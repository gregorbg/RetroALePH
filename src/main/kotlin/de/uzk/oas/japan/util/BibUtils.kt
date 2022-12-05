package de.uzk.oas.japan.util

import de.uzk.oas.japan.catalogue.model.HbzId
import de.uzk.oas.japan.catalogue.model.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.model.lobid.HasItem
import de.uzk.oas.japan.catalogue.model.lobid.Subject
import de.uzk.oas.japan.catalogue.model.raw.mab.AlephMab2
import de.uzk.oas.japan.catalogue.model.raw.marc.AlmaMarc21
import de.uzk.oas.japan.library.BookSignature
import de.uzk.oas.japan.library.LibrarySection
import de.uzk.oas.japan.util.StringUtils.containsAllInOrder
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import org.apache.commons.lang3.StringUtils
import java.io.File
import java.util.zip.GZIPInputStream

object BibUtils {
    const val URL_FREIE_VERSCHLAGWORTUNG = "http://www.wikidata.org/entity/Q47524318"
    const val IDENTIFIER_OAS_LIBRARY = "DE-38-459"

    val HBZ_HT_PATTERN = "HT\\d{9}".toRegex()

    val FOLDER_TMP = File("/tmp")

    val KTOR_CLIENT = HttpClient(Java)

    val Subject.isFreieVerschlagwortung: Boolean
        get() = source?.id == URL_FREIE_VERSCHLAGWORTUNG

    fun List<HasItem>.findHeldByLibrary(libraryId: String) =
        find { "$libraryId#" in it.heldBy?.id.orEmpty() }

    fun List<HasItem>.findOASLibrary() =
        findHeldByLibrary(IDENTIFIER_OAS_LIBRARY)

    fun BibliographicResource.findOASSignature() =
        hasItem.findOASLibrary()?.callNumber

    fun BibliographicResource.isValidSignatureTag(
        signatureTag: String,
        superordinateMap: Map<HbzId, BibliographicResource> = emptyMap()
    ): Boolean {
        for (contributor in contributions) {
            val agent = contributor.agent
            val allNames = agent.labels + agent.alternativeLabels

            if ("CorporateBody" in agent.types) {
                for (corporateName in allNames) {
                    val lcName = StringUtils.stripAccents(corporateName).lowercase()

                    if (lcName.containsAllInOrder(signatureTag, true)) {
                        return true
                    }
                }
            }

            if ("Person" in agent.types) {
                for (personName in allNames) {
                    val nameParts = if ("," in personName) personName.split(",", limit = 2) else listOf(personName, "")
                    val (cleanLast, cleanFirst) = nameParts.map { StringUtils.stripAccents(it).trim() }

                    if (signatureTag == cleanLast.take(3))
                        return true

                    if (signatureTag == "${cleanLast.take(3)}${cleanFirst.take(1)}")
                        return true

                    if (signatureTag == "${cleanLast.take(2)}${cleanFirst.take(2)}")
                        return true
                }
            }
        }

        val allTitles = listOf(title) + alternativeTitles + otherTitleInformation

        for (title in allTitles) {
            val lcTitle = StringUtils.stripAccents(title).lowercase()

            if (lcTitle.containsAllInOrder(signatureTag, true)) {
                return true
            }
        }

        for (partOf in isPartOf) {
            if ("IsPartOfRelation" in partOf.types) {
                for (superordinate in partOf.hasSuperordinate) {
                    val urlId = superordinate.id.orEmpty()

                    val htMatches = HBZ_HT_PATTERN.find(urlId) ?: continue
                    val htNumber = HbzId(htMatches.value)

                    val superordinateBibResource = superordinateMap[htNumber] ?: continue

                    if (superordinateBibResource.isValidSignatureTag(signatureTag, superordinateMap))
                        return true
                }
            }
        }

        return false
    }

    fun <T> generateSignatureDistribution(
        signatures: Map<T, BookSignature>,
        kindMapping: (T) -> String
    ): Map<LibrarySection, Map<String, Int>> {
        return signatures.entries.groupBy { it.value.section }
            .mapValues { it.value.groupBy { td -> kindMapping(td.key) }.mapValues { td -> td.value.size } }
        //.mapKeys { it.key?.signatureTag }
    }

    fun loadCachedJapBestand(cacheFolder: File = FOLDER_TMP): List<String> {
        val japBestandFile = File(cacheFolder, "japbestand.hbz")

        if (!japBestandFile.exists()) {
            println("Querying LOBID API!")

            runBlocking {
                val gzipResponse = KTOR_CLIENT.use {
                    it.get("https://lobid.org/resources/search?owner=$IDENTIFIER_OAS_LIBRARY&format=jsonl") {
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
        return loadCachedJapBestand(cacheFolder)
            .map { Json.decodeFromString(it) }
    }

    fun loadAlephMab(book: BibliographicResource): AlephMab2 {
        val url = "https://lobid.org/hbz01/${book.hbzId}"

        val mabRaw = runBlocking {
            KTOR_CLIENT.use {
                it.get(url).bodyAsText()
            }
        }

        return XML.decodeFromString(mabRaw)
    }

    fun loadAlmaMarc(book: BibliographicResource): AlmaMarc21? {
        val almaMms = book.almaMmsId ?: return null
        val url = "https://alma.lobid.org/marcxml/$almaMms"

        val marcRaw = runBlocking {
            KTOR_CLIENT.use {
                it.get(url).bodyAsText()
            }
        }

        return XML.decodeFromString(marcRaw)
    }

    fun loadAlmaResource(book: BibliographicResource): BibliographicResource {
        if (book.almaMmsId != null)
            return book

        val url = "https://test.lobid.org/resources/${book.hbzId}.json"

        val jsonRaw = runBlocking {
            KTOR_CLIENT.use {
                it.get(url).bodyAsText()
            }
        }

        return Json.decodeFromString(jsonRaw)
    }
}