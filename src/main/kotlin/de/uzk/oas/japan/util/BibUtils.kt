package de.uzk.oas.japan.util

import de.uzk.oas.japan.catalogue.model.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.model.lobid.HasItem
import de.uzk.oas.japan.catalogue.model.lobid.JsonLd
import de.uzk.oas.japan.catalogue.model.lobid.Subject
import de.uzk.oas.japan.library.BookSignature
import de.uzk.oas.japan.library.LibrarySection
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.zip.GZIPInputStream

object BibUtils {
    const val URL_FREIE_VERSCHLAGWORTUNG = "http://www.wikidata.org/entity/Q47524318"
    const val IDENTIFIER_OAS_LIBRARY = "DE-38-459#"

    val FOLDER_TMP = File("/tmp")

    val KTOR_CLIENT = HttpClient(Java)

    val Subject.isFreieVerschlagwortung: Boolean
        get() = source?.id == URL_FREIE_VERSCHLAGWORTUNG

    fun List<HasItem>.findOASLibrary() =
        find { IDENTIFIER_OAS_LIBRARY in it.heldBy?.id.orEmpty() }

    fun BibliographicResource.findOASSignature() =
        hasItem.findOASLibrary()?.callNumber

    fun <T> generateSignatureDistribution(
        signatures: Map<T, BookSignature?>,
        kindMapping: (T) -> String
    ): Map<LibrarySection?, Map<String, Int>> {
        return signatures.entries.groupBy { it.value?.section }
            .mapValues { it.value.groupBy { td -> kindMapping(td.key) }.mapValues { td -> td.value.size } }
        //.mapKeys { it.key?.signatureTag }
    }

    fun loadCachedJapBestand(cacheFolder: File = FOLDER_TMP): List<String> {
        val japBestandFile = File(cacheFolder, "japbestand.hbz")

        if (!japBestandFile.exists()) {
            println("Querying LOBID API!")

            runBlocking {
                val gzipResponse = KTOR_CLIENT.use {
                    it.get<HttpResponse>("https://lobid.org/resources/search?owner=DE-38-459&format=jsonl") {
                        header("Accept-Encoding", "gzip")
                    }
                }

                japBestandFile.writeBytes(gzipResponse.readBytes())
            }
        }

        val gzipInput = GZIPInputStream(japBestandFile.inputStream())

        val stringBytes = gzipInput.use { it.readBytes() }
        val jsonLines = stringBytes.decodeToString()

        return jsonLines.lines().filter { it.isNotBlank() }
    }

    fun parseCachedJapBestand(cacheFolder: File = FOLDER_TMP): List<BibliographicResource> {
        val bestand = loadCachedJapBestand(cacheFolder)

        return bestand.map { Json.decodeFromString<JsonLd>(it) }
            .filterIsInstance<BibliographicResource>()
    }
}