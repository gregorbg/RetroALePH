package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.IdProvider
import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.catalogue.lobid.BibInstitution
import de.uzk.oas.japan.catalogue.lobid.BibResource
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
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import kotlin.io.use

class LobidApi(val storage: BibDataStorage) : BibDataSource, BibInstitutionSource {
    override fun loadBestand(institutionIsil: IsilSeal): List<BibResource> {
        return storage.loadBestand(institutionIsil) ?: runBlocking {
            KTOR_CLIENT.use { ktor ->
                val gzipBytes = ktor.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BASE_HOST

                        appendPathSegments("resources", "search")

                        parameter("owner", institutionIsil.id)
                        parameter("format", "jsonl")
                    }

                    header("Accept-Encoding", "gzip")
                }.bodyAsChannel()

                FileUtils.decodeGzipStream(gzipBytes.toInputStream()).useLines { lines ->
                    lines.map { Json.decodeFromString<BibResource>(it) }.toList()
                }.also { storage.storeBestand(institutionIsil, it) }
            }
        }
    }

    override fun loadResource(hbzId: HbzId): BibResource? {
        return storage.loadResource(hbzId)
            ?: fetchAndStore<BibResource>("resources", hbzId, Json) { storage.storeResource(hbzId, it) }
    }

    override fun loadAlmaMarc(almaMmsId: AlmaMmsId): AlmaMarc21? {
        return storage.loadAlmaMarc(almaMmsId)
            ?: fetchAndStore<AlmaMarc21>("marcxml", almaMmsId, XML) { storage.storeAlmaMarc(almaMmsId, it) }
    }

    override fun loadInstitution(institutionIsil: IsilSeal): BibInstitution? {
        return fetchAndStore("organisations", institutionIsil, Json)
    }

    private inline fun <reified T> fetchAndStore(
        subPath: String,
        bookId: IdProvider,
        decoder: StringFormat,
        crossinline cacheFn: (T) -> Unit = {}
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
