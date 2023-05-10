package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IdProvider
import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.util.FileUtils
import kotlinx.serialization.*
import java.io.File

class BibFileStorage<ID : IdProvider, T>(
    private val rootFolder: File,
    itemFolderName: String,
    private val serialFormat: StringFormat,
    private val serializer: KSerializer<T>,
) : BibHoldingStorage<T>, BibItemStorage<ID, T> {
    init {
        rootFolder.mkdirs()
    }

    private val itemFolder = File(rootFolder, itemFolderName).also { it.mkdirs() }

    private fun bestandFileFor(institutionIsil: IsilSeal) =
        File(this.rootFolder, "${institutionIsil.id}.bestand.hbz")

    override fun loadBestand(institutionIsil: IsilSeal): List<T>? {
        val bestandFile = bestandFileFor(institutionIsil)

        if (!bestandFile.exists()) {
            return null
        }

        return FileUtils.decodeGzipStream(bestandFile.inputStream()).useLines { lines ->
            lines.map { serialFormat.decodeFromString(serializer, it) }.toList()
        }
    }

    override fun storeBestand(institutionIsil: IsilSeal, bestand: List<T>) {
        val bestandFile = bestandFileFor(institutionIsil)

        FileUtils.encodeGzipStream(bestandFile.outputStream()).use { writer ->
            bestand.forEach { writer.appendLine(serialFormat.encodeToString(serializer, it)) }
        }
    }

    override fun storeResource(bookId: ID, book: T) {
        val cacheFile = File(itemFolder, bookId.id)

        val dataSerial = serialFormat.encodeToString(serializer, book)
        cacheFile.writeText(dataSerial)
    }

    override fun loadResource(id: ID): T? {
        val cacheFile = File(itemFolder, id.id)

        val fileContent = cacheFile.takeIf { it.exists() }
            ?.readText()
            ?: return null

        return serialFormat.decodeFromString(serializer, fileContent)
    }

    companion object {
        const val FOLDER_MARC21 = "marc21"
        const val FOLDER_RESOURCES = "resources"
    }
}