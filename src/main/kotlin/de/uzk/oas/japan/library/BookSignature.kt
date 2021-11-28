package de.uzk.oas.japan.library

sealed class BookSignature {
    abstract val section: LibrarySection
    abstract val index: Int
    abstract val subIndex: Int?

    data class Standard(
        override val section: LibrarySection,
        val category: Int,
        val subCategory: Int? = null,
        val authorTag: String,
        override val index: Int,
        override val subIndex: Int? = null
    ) : BookSignature()

    data class JaDe(
        override val index: Int,
        override val subIndex: Int? = null
    ) : BookSignature() {
        override val section get() = LibrarySection.JADE
    }

    data class Magazine(
        override val index: Int
    ) : BookSignature() {
        override val section get() = LibrarySection.MAGAZINES
        override val subIndex: Int? get() = null
    }

    companion object {
        const val HBZ_SEAL = "JAP/"

        fun parseNew(rawSignature: String): BookSignature? {
            if (rawSignature.isBlank())
                return null

            // TODO This is bad! Alert!
            if (rawSignature.matches("^0+.*".toRegex()))
                return null

            if (rawSignature.startsWith(HBZ_SEAL, ignoreCase = false))
                return parseNew(rawSignature.substringAfter(HBZ_SEAL))

            // TODO implement proper support for book copies (Zweitexemplare)
            if (rawSignature.contains('#'))
                return parseNew(rawSignature.substringBefore('#'))

            if (rawSignature.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
                val indexSpec = rawSignature.substring(LibrarySection.JADE.signatureTag.length)
                val (index, subIndex) = parseIndexSpec(indexSpec, "-")

                return JaDe(index, subIndex)
            }

            if (rawSignature.startsWith(LibrarySection.MAGAZINES.signatureTag, ignoreCase = true)) {
                // TODO this trim() is bad! Alert!
                val index = rawSignature.substring(LibrarySection.MAGAZINES.signatureTag.length).trim().toInt()
                return Magazine(index)
            }

            val signatureMatch = "(\\w+?)(\\d+(?:-\\d+)?)(\\w+)(\\d+(?:-\\d+)?)".toRegex().matchEntire(rawSignature) ?: return null
            val (signatureTag, categorySpec, authorTag, indexSpec) = signatureMatch.destructured

            val section = LibrarySection.fromSignatureTag(signatureTag)

            val (category, subCategory) = parseIndexSpec(categorySpec, "-")
            val (index, subIndex) = parseIndexSpec(indexSpec, "-")

            return Standard(section, category, subCategory, authorTag, index, subIndex)
        }

        fun parseOld(rawSignature: String): BookSignature? {
            if (rawSignature.isBlank())
                return null

            if (rawSignature.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
                val indexSpec = rawSignature.split("\\s".toRegex()).last()
                val (index, subIndex) = parseIndexSpec(indexSpec, ".")

                return JaDe(index, subIndex)
            }

            val (signatureTag, categorySpec, authorTag, indexSpec) = rawSignature.trim().split("\\s+".toRegex())

            val section = LibrarySection.fromSignatureTag(signatureTag)

            val (category, subCategory) = parseIndexSpec(categorySpec, ".")
            val (index, subIndex) = parseIndexSpec(indexSpec, ".")

            return Standard(section, category, subCategory, authorTag, index, subIndex)
        }

        private fun parseIndexSpec(indexSpec: String, delimiter: String): Pair<Int, Int?> {
            // Hack for aggregated MtM entries in old SD signatures
            if (delimiter == "." && "-" in indexSpec) {
                val (firstInRow, _) = indexSpec.split("-")
                return parseIndexSpec(firstInRow, delimiter)
            }

            if (delimiter in indexSpec) {
                val (index, subIndex) = indexSpec.split(delimiter).map { it.toInt() }
                return index to subIndex
            }

            return indexSpec.toInt() to null
        }
    }
}
