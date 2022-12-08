package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.mab.AlephMab2
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21
import de.uzk.oas.japan.util.FileUtils
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File

class BibFileStorage(val rootFolder: File) : BibDataStorage {
    init {
        rootFolder.mkdirs()
    }

    private val bestandFile: File
        get() = File(rootFolder, FILENAME_BESTAND)

    override val cachedBestand: List<BibliographicResource>?
        get() = bestandFile.takeIf { it.exists() }
            ?.readBytes()
            ?.let { FileUtils.decodeGzipString(it) }
            ?.lines()
            ?.map { Json.decodeFromString(it) }

    override fun cacheBestand(lobidBestand: List<BibliographicResource>) {
        val jsonLines = lobidBestand.joinToString("\n") { Json.encodeToString(it) }
        val gzipBytes = FileUtils.encodeGzip(jsonLines)

        bestandFile.writeBytes(gzipBytes)
    }

    override fun cacheAlephMab(book: BibliographicResource, mab: AlephMab2) =
        cacheInternal(book, FOLDER_MAB2, XML, mab)

    override fun cacheAlmaMarc(book: BibliographicResource, marc: AlmaMarc21) =
        cacheInternal(book, FOLDER_MARC21, XML, marc)

    override fun cacheAlmaResource(book: BibliographicResource) =
        cacheInternal(book, FOLDER_ALMA_UPCYCLE, Json, book)

    private inline fun <reified T> cacheInternal(
        book: BibliographicResource,
        folderName: String,
        encoder: StringFormat,
        data: T
    ) {
        val cacheFolder = File(rootFolder, folderName).also { it.mkdirs() }
        val cacheFile = File(cacheFolder, book.hbzId)

        val dataSerial = encoder.encodeToString(data)
        cacheFile.writeText(dataSerial)
    }

    override fun loadAlephMab(book: BibliographicResource): AlephMab2? = loadInternal(book, FOLDER_MAB2, XML)
    override fun loadAlmaMarc(book: BibliographicResource): AlmaMarc21? = loadInternal(book, FOLDER_MARC21, XML)
    override fun upcycleToAlmaResource(book: BibliographicResource): BibliographicResource? = loadInternal(book, FOLDER_ALMA_UPCYCLE, Json)

    private inline fun <reified T> loadInternal(
        book: BibliographicResource,
        folderName: String,
        decoder: StringFormat,
    ): T? {
        val cacheFolder = File(rootFolder, folderName)
        val cacheFile = File(cacheFolder, book.hbzId)

        val fileContent = cacheFile.takeIf { it.exists() }
            ?.readText()
            ?: return null

        return decoder.decodeFromString(fileContent)
    }

    companion object {
        const val FILENAME_BESTAND = "japbestand.hbz"

        const val FOLDER_MAB2 = "mab2"
        const val FOLDER_MARC21 = "marc21"
        const val FOLDER_ALMA_UPCYCLE = "alma-upcycle"
    }
}