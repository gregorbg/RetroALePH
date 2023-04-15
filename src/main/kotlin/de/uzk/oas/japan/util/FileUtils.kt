package de.uzk.oas.japan.util

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object FileUtils {
    fun decodeGzipStream(gzip: InputStream): BufferedReader {
        return GZIPInputStream(gzip).bufferedReader()
    }

    fun encodeGzipStream(target: OutputStream): BufferedWriter {
        return GZIPOutputStream(target).bufferedWriter()
    }
}
