package de.uzk.oas.japan.catalogue.format

enum class TableExportFormat(
    val separator: String,
    val cleanup: (String) -> String,
    private val prefix: String? = null,
    private val suffix: String? = null,
    val headerSep: Char? = null
) {
    CSV(TableExportFormat.SEPARATOR_CSV, { TableExportFormat.cleanCsv(it) }),
    MARKDOWN(
        TableExportFormat.SEPARATOR_MD,
        { TableExportFormat.cleanMarkdown(it) },
        TableExportFormat.SEPARATOR_MD,
        TableExportFormat.SEPARATOR_MD,
        TableExportFormat.HEADER_SEP_MD
    );

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

        fun cleanCsv(original: String): String = original.replace("\"", "\"\"").run {
            if (SEPARATOR_CSV in this) "\"$this\"" else this
        }

        fun cleanMarkdown(original: String): String = original.replace("|", "&#124;")
    }
}