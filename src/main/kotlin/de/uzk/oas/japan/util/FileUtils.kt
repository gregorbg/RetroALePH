package de.uzk.oas.japan.util

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object FileUtils {
    fun decodeGzipString(gzip: ByteArray): String {
        return GZIPInputStream(gzip.inputStream()).bufferedReader().use { it.readText() }
    }

    fun encodeGzip(content: String): ByteArray {
        val outputBaos = ByteArrayOutputStream()

        GZIPOutputStream(outputBaos).bufferedWriter().use { it.write(content) }

        return outputBaos.toByteArray()
    }
}