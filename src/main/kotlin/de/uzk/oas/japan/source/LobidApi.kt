package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.IdProvider
import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21
import de.uzk.oas.japan.util.FileUtils
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import kotlin.io.use

class LobidApi(val storage: BibDataStorage) : BibDataSource {
    override fun loadBestand(institutionId: String): List<BibliographicResource> {
        return storage.loadBestand(institutionId) ?: runBlocking {
            val gzipBytes = KTOR_CLIENT.use {
                it.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BASE_HOST

                        appendPathSegments("resources", "search")

                        parameter("owner", institutionId)
                        parameter("format", "jsonl")
                    }

                    header("Accept-Encoding", "gzip")
                }.readBytes()
            }

            val jsonLines = FileUtils.decodeGzipString(gzipBytes)
                .lines().filter { it.isNotBlank() }

            jsonLines.map { Json.decodeFromString<BibliographicResource>(it) }
                .also { storage.storeBestand(institutionId, it) }
        }
    }

    override fun loadResource(hbzId: HbzId): BibliographicResource? {
        return storage.loadResource(hbzId)
            ?: fetchAndStore<BibliographicResource>("resources", hbzId, Json) { storage.storeResource(hbzId, it) }
    }

    override fun loadAlmaMarc(almaMmsId: AlmaMmsId): AlmaMarc21? {
        return storage.loadAlmaMarc(almaMmsId)
            ?: fetchAndStore<AlmaMarc21>("marcxml", almaMmsId, XML) { storage.storeAlmaMarc(almaMmsId, it) }
    }

    private inline fun <reified T> fetchAndStore(
        subPath: String,
        bookId: IdProvider,
        decoder: StringFormat,
        crossinline cacheFn: (T) -> Unit
    ): T? {
        return try {
            runBlocking {
                val jsonRaw = KTOR_CLIENT.use {
                    it.get {
                        url {
                            protocol = URLProtocol.HTTPS
                            host = BASE_HOST

                            appendPathSegments(subPath, bookId.id)
                        }
                    }.bodyAsText()
                }

                decoder.decodeFromString<T>(jsonRaw)
                    .also { cacheFn(it) }
            }
        } catch (e: ClientRequestException) {
            return null
        }
    }

    companion object {
        // TODO Alma revert URL
        const val BASE_HOST = "alma.lobid.org"

        private val KTOR_CLIENT
            get() = HttpClient(Java) {
                expectSuccess = true

                install(ContentNegotiation) {
                    json()
                }
            }
    }
}
