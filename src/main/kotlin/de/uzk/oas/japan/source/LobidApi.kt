package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.mab.AlephMab2
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21
import de.uzk.oas.japan.util.FileUtils
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML

class LobidApi(val storage: BibDataStorage) : BibDataSource {
    private val KTOR_CLIENT get() = HttpClient(Java) {
        expectSuccess = true

        install(ContentNegotiation) {
            json()
        }
    }

    fun loadBestand(institutionId: String): List<BibliographicResource> {
        return storage.retrieveCachedBestand(institutionId) ?: runBlocking {
            val gzipBytes = KTOR_CLIENT.use {
                it.get("https://lobid.org/resources/search?owner=${institutionId}&format=jsonl") {
                    header("Accept-Encoding", "gzip")
                }.readBytes()
            }

            val jsonLines = FileUtils.decodeGzipString(gzipBytes)
                .lines().filter { it.isNotBlank() }

            jsonLines.map { Json.decodeFromString<BibliographicResource>(it) }
                .also { storage.cacheBestand(institutionId, it) }
        }
    }

    fun retrieve(hbzId: HbzId): BibliographicResource? {
        return try {
            runBlocking {
                KTOR_CLIENT.use {
                    it.get("https://lobid.org/resources/${hbzId.id}") {
                        header("Accept", "application/json")
                    }.body<BibliographicResource>()
                }
            }
        } catch (e: ClientRequestException) {
            return null
        }
    }

    override fun loadAlephMab(book: BibliographicResource): AlephMab2 {
        val url = "https://lobid.org/hbz01/${book.hbzId}"

        return storage.loadAlephMab(book)
            ?: fetchAndStore(book, url, XML, storage::cacheAlephMab)
    }

    override fun loadAlmaMarc(book: BibliographicResource): AlmaMarc21 {
        if (book.almaMmsId != null) {
            return loadAlmaMarcInternal(book)
        }

        val almaUpcycle = this.upcycleToAlmaResource(book)
        return loadAlmaMarcInternal(almaUpcycle)
    }

    private fun loadAlmaMarcInternal(definitelyAlmaBook: BibliographicResource): AlmaMarc21 {
        val url = "https://alma.lobid.org/marcxml/${definitelyAlmaBook.almaMmsId}"

        return storage.loadAlmaMarc(definitelyAlmaBook)
            ?: fetchAndStore(definitelyAlmaBook, url, XML, storage::cacheAlmaMarc)
    }

    override fun upcycleToAlmaResource(book: BibliographicResource): BibliographicResource {
        if (book.almaMmsId != null)
            return book

        val url = "https://test.lobid.org/resources/${book.hbzId}.json"

        return storage.upcycleToAlmaResource(book)
            ?: fetchAndStore(book, url, Json, storage::cacheAlmaResource)
    }

    private inline fun <reified T> fetchAndStore(
        book: BibliographicResource,
        url: String,
        decoder: StringFormat,
        crossinline cacheFn: (BibliographicResource, T) -> Unit
    ): T {
        return runBlocking {
            val jsonRaw = KTOR_CLIENT.use {
                it.get(url).bodyAsText()
            }

            decoder.decodeFromString<T>(jsonRaw)
                .also { cacheFn(book, it) }
        }
    }

    private inline fun <reified T> fetchAndStore(
        book: BibliographicResource,
        url: String,
        decoder: StringFormat,
        crossinline cacheFn: (T) -> Unit
    ): T = fetchAndStore(book, url, decoder) { _, data -> cacheFn(data) }
}
