package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.IdProvider
import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
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

    private fun bestandFileFor(institutionId: String) =
        File(this.rootFolder, "${institutionId}.bestand.hbz")

    override fun loadBestand(institutionId: String): List<BibliographicResource>? {
        val bestandFile = bestandFileFor(institutionId)

        if (!bestandFile.exists()) {
            return null
        }

        return FileUtils.decodeGzipStream(bestandFile.inputStream()).useLines { lines ->
            lines.map { Json.decodeFromString<BibliographicResource>(it) }.toList()
        }
    }

    override fun storeBestand(institutionId: String, lobidBestand: List<BibliographicResource>) {
        val bestandFile = bestandFileFor(institutionId)

        FileUtils.encodeGzipStream(bestandFile.outputStream()).use { writer ->
            lobidBestand.forEach { writer.appendLine(Json.encodeToString(it)) }
        }
    }

    override fun storeAlmaMarc(bookId: AlmaMmsId, marc: AlmaMarc21) =
        cacheInternal(bookId, FOLDER_MARC21, XML, marc)

    override fun storeResource(bookId: HbzId, book: BibliographicResource) =
        cacheInternal(bookId, FOLDER_RESOURCES, Json, book)

    private inline fun <reified T> cacheInternal(
        bookId: IdProvider,
        folderName: String,
        encoder: StringFormat,
        data: T
    ) {
        val cacheFolder = File(rootFolder, folderName).also { it.mkdirs() }
        val cacheFile = File(cacheFolder, bookId.id)

        val dataSerial = encoder.encodeToString(data)
        FileUtils.encodeGzipStream(cacheFile.outputStream()).use { it.appendLine(dataSerial) }
    }

    override fun loadResource(hbzId: HbzId): BibliographicResource? = loadInternal(hbzId, FOLDER_RESOURCES, Json)

    override fun loadAlmaMarc(almaMmsId: AlmaMmsId): AlmaMarc21? = loadInternal(almaMmsId, FOLDER_MARC21, XML)

    private inline fun <reified T> loadInternal(
        bookId: IdProvider,
        folderName: String,
        decoder: StringFormat,
    ): T? {
        val cacheFolder = File(rootFolder, folderName)
        val cacheFile = File(cacheFolder, bookId.id)

        val fileContent = cacheFile.takeIf { it.exists() }
            ?.readText()
            ?: return null

        return decoder.decodeFromString(fileContent)
    }

    companion object {
        const val FOLDER_MARC21 = "marc21"
        const val FOLDER_RESOURCES = "resources"
    }
}