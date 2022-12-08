package de.uzk.oas.japan.export

sealed class TableExportFormat(
    val separator: String,
    private val prefix: String? = null,
    private val suffix: String? = null,
    val headerSep: Char? = null
) {
    object Csv : TableExportFormat(SEPARATOR_CSV) {
        override fun cleanup(original: String): String {
            return original.replace("\"", "\"\"").run {
                if (SEPARATOR_CSV in this) "\"$this\"" else this
            }
        }
    }

    object Markdown : TableExportFormat(SEPARATOR_MD, SEPARATOR_MD, SEPARATOR_MD, HEADER_SEP_MD) {
        override fun cleanup(original: String): String {
            return original.replace(SEPARATOR_MD, "&#124;")
        }

    }

    abstract fun cleanup(original: String): String

    fun joinElements(elements: List<String>): String {
        return elements.joinToString(this.separator, prefix = this.prefix.orEmpty(), postfix = this.suffix.orEmpty())
    }

    fun generateHeaderSep(elements: List<String>): String {
        if (headerSep == null)
            return ""

        val headerReplacements = elements.map { headerSep.toString().repeat(it.length) }
        return joinElements(headerReplacements)
    }

    companion object {
        const val SEPARATOR_CSV = ";"
        const val SEPARATOR_MD = "|"

        const val HEADER_SEP_MD = '-'
    }
}