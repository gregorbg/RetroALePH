package de.uzk.oas.japan.util

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object FileUtils {
    fun decodeGzipString(gzip: ByteArray): String {
        val gzipInput = GZIPInputStream(gzip.inputStream())
        val stringBytes = gzipInput.use { it.readBytes() }

        return stringBytes.decodeToString()
    }

    fun encodeGzip(content: String): ByteArray {
        val outputBaos = ByteArrayOutputStream()

        val gzipOutput = GZIPOutputStream(outputBaos)
        gzipOutput.use { it.write(content.encodeToByteArray()) }

        return outputBaos.toByteArray()
    }
}