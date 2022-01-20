package de.uzk.oas.japan.library

sealed interface BookSignature {
    val section: LibrarySection
    val index: Int
    val subIndex: Int?

    data class Standard(
        override val section: LibrarySection,
        val category: Int,
        val subCategories: List<Int> = emptyList(),
        val authorTag: String,
        override val index: Int,
        override val subIndex: Int? = null
    ) : BookSignature

    data class JaDe(
        override val index: Int,
        override val subIndex: Int? = null
    ) : BookSignature {
        override val section get() = LibrarySection.JADE
    }

    data class Magazine(
        override val index: Int
    ) : BookSignature {
        override val section get() = LibrarySection.MAGAZINES
        override val subIndex: Int? get() = null
    }

    data class ReplicateItem(val signature: BookSignature, val number: Int) : BookSignature by signature

    companion object {
        const val HBZ_SEAL = "JAP/"
        val HBZ_SIGNATURE_REGEX = "(\\w+?)(\\d(?:-\\d+)*)(\\w+)(\\d+(?:-\\d+)?)".toRegex()

        fun parseNew(rawSignature: String): BookSignature? {
            if (rawSignature.isBlank())
                return null

            // TODO This is bad! Alert!
            if (rawSignature.matches("^0+.*".toRegex()))
                return null

            if (rawSignature.startsWith(HBZ_SEAL, ignoreCase = false))
                return parseNew(rawSignature.substringAfter(HBZ_SEAL))

            if (rawSignature.contains('#')) {
                val (fullSignatureSpec, replicationSpec) = rawSignature.split('#')

                val originalSignature = parseNew(fullSignatureSpec) ?: return null
                val itemCount = replicationSpec.lowercase().first() - 'a'

                return ReplicateItem(originalSignature, itemCount)
            }

            if (rawSignature.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
                val indexSpec = rawSignature.substring(LibrarySection.JADE.signatureTag.length)
                val (index, subIndex) = parseIndexSpec(indexSpec, "-")

                return JaDe(index, subIndex.singleOrNull())
            }

            if (rawSignature.startsWith(LibrarySection.MAGAZINES.signatureTag, ignoreCase = true)) {
                // TODO this trim() is bad! Alert!
                val index = rawSignature.substring(LibrarySection.MAGAZINES.signatureTag.length).trim().toInt()
                return Magazine(index)
            }

            val signatureMatch = HBZ_SIGNATURE_REGEX.matchEntire(rawSignature) ?: return null
            val (signatureTag, categorySpec, authorTag, indexSpec) = signatureMatch.destructured

            val section = LibrarySection.fromSignatureTag(signatureTag)

            val (category, subCategories) = parseIndexSpec(categorySpec, "-")
            val (index, subIndex) = parseIndexSpec(indexSpec, "-")

            return Standard(section, category, subCategories, authorTag, index, subIndex.singleOrNull())
        }

        fun parseOld(rawSignature: String): BookSignature? {
            if (rawSignature.isBlank())
                return null

            if (rawSignature.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
                val indexSpec = rawSignature.split("\\s".toRegex()).last()
                val (index, subIndex) = parseIndexSpec(indexSpec, ".")

                return JaDe(index, subIndex.singleOrNull())
            }

            val (signatureTag, categorySpec, authorTag, indexSpec) = rawSignature.trim().split("\\s+".toRegex())

            val section = LibrarySection.fromSignatureTag(signatureTag)

            val (category, subCategory) = parseIndexSpec(categorySpec, ".")
            val (index, subIndex) = parseIndexSpec(indexSpec, ".")

            return Standard(section, category, subCategory, authorTag, index, subIndex.singleOrNull())
        }

        private fun parseIndexSpec(indexSpec: String, delimiter: String): Pair<Int, List<Int>> {
            // Hack for aggregated MtM entries in old SD signatures
            if (delimiter == "." && "-" in indexSpec) {
                val (firstInRow, _) = indexSpec.split("-")
                return parseIndexSpec(firstInRow, delimiter)
            }

            if (delimiter in indexSpec) {
                val (index, subValueGroup) = indexSpec.split(delimiter, limit = 2)
                val subValues = subValueGroup.split(delimiter).map { it.toInt() }

                return index.toInt() to subValues
            }

            return indexSpec.toInt() to emptyList()
        }
    }
}
