package de.uzk.oas.japan.util

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object FileUtils {
    fun decodeGzipString(gzip: ByteArray): String {
        return decodeGzipStream(gzip.inputStream()).use { it.readText() }
    }

    fun decodeGzipStream(gzip: InputStream): BufferedReader {
        return GZIPInputStream(gzip).bufferedReader()
    }

    fun encodeGzip(content: String): ByteArray {
        val outputBaos = ByteArrayOutputStream()

        GZIPOutputStream(outputBaos).bufferedWriter().use { it.write(content) }

        return outputBaos.toByteArray()
    }
}