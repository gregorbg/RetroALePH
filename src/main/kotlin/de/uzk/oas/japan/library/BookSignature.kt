package de.uzk.oas.japan.library

sealed interface BookSignature {
    val section: LibrarySection
    val index: Int
    val subIndices: List<Int>

    data class Standard(
        override val section: LibrarySection,
        val category: Int,
        val subCategories: List<Int> = emptyList(),
        val authorTag: String,
        override val index: Int,
        override val subIndices: List<Int> = emptyList()
    ) : BookSignature

    data class JaDe(
        override val index: Int,
        override val subIndices: List<Int> = emptyList()
    ) : BookSignature {
        override val section get() = LibrarySection.JADE
    }

    data class Magazine(
        override val index: Int
    ) : BookSignature {
        override val section get() = LibrarySection.MAGAZINES
        override val subIndices get() = emptyList<Int>()
    }

    data class ReplicateItem(val signature: BookSignature, val number: Int) : BookSignature by signature

    companion object {
        const val HBZ_SEAL = "JAP/"

        // second group optional dash at the beginning (\d-??) is a HACK
        // by consensus (which I voted against) we sign items with NO sub-category
        // as JAP/Abc4-Author (formally wrong 4-) instead of JAP/Abc4Author
        val OAS_SIGNATURE_REGEX = "([A-Za-z]+?)(\\d-??(?:-\\d+)*)([A-Za-z]+)(\\d+(?:-\\d+)*)".toRegex()

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

                return JaDe(index, subIndex)
            }

            if (rawSignature.startsWith(LibrarySection.MAGAZINES.signatureTag, ignoreCase = true)) {
                // TODO this trim() is bad! Alert!
                val index = rawSignature.substring(LibrarySection.MAGAZINES.signatureTag.length).trim().toInt()
                return Magazine(index)
            }

            val signatureMatch = OAS_SIGNATURE_REGEX.matchEntire(rawSignature) ?: return null
            val (signatureTag, categorySpec, authorTag, indexSpec) = signatureMatch.destructured

            val section = LibrarySection.fromSignatureTag(signatureTag)

            val (category, subCategories) = parseIndexSpec(categorySpec, "-")
            val (index, subIndex) = parseIndexSpec(indexSpec, "-")

            return Standard(section, category, subCategories, authorTag, index, subIndex)
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

        private fun parseIndexSpec(indexSpec: String, delimiter: String): Pair<Int, List<Int>> {
            // Hack for aggregated MtM entries in old SD signatures
            if (delimiter == "." && "-" in indexSpec) {
                val (firstInRow, _) = indexSpec.split("-")
                return parseIndexSpec(firstInRow, delimiter)
            }

            if (delimiter in indexSpec) {
                val (index, subValueGroup) = indexSpec.split(delimiter, limit = 2)
                val subValues = subValueGroup.split(delimiter)
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }

                return index.toInt() to subValues
            }

            return indexSpec.toInt() to emptyList()
        }
    }
}
