package de.uzk.oas.japan.library.signature

import de.uzk.oas.japan.library.signature.data.LibrarySection

object SignatureParser {
    const val HBZ_OAS_SEAL = "JAP/"
    const val REPLICATE_ITEM_INDICATOR = '#'

    // second group optional dash at the beginning (\d-??) is a HACK
    // by consensus (which I voted against) we sign items with NO sub-category
    // as JAP/Abc4-Author (formally wrong 4-) instead of JAP/Abc4Author
    val OAS_SIGNATURE_REGEX = "([A-Z][a-z]*?)(\\d-??(?:-\\d+)*)([A-Za-z]+)(\\d+(?:-\\d+)*)".toRegex()

    fun parseHbzFormat(rawSignature: String): BookSignature {
        if (rawSignature.isBlank())
            return BookSignature.Faulty(rawSignature, "Raw signature is blank")

        if (rawSignature.matches("^0+.*".toRegex()))
            return BookSignature.Faulty(rawSignature, "Raw signature is a serial number")

        if (rawSignature.startsWith(HBZ_OAS_SEAL, ignoreCase = false))
            return parseHbzFormat(rawSignature.substringAfter(HBZ_OAS_SEAL))

        if (rawSignature.contains(REPLICATE_ITEM_INDICATOR)) {
            val (fullSignatureSpec, replicationSpec) = rawSignature.split(REPLICATE_ITEM_INDICATOR)

            val originalSignature = parseHbzFormat(fullSignatureSpec)
            val itemCount = replicationSpec.lowercase().first() - 'a' // replications are "counted" alphabetically

            if (originalSignature is BookSignature.Faulty) {
                return BookSignature.Faulty(
                    rawSignature,
                    "Faulty signature in front of $REPLICATE_ITEM_INDICATOR indicator: ${originalSignature.errorMessage}",
                    originalSignature.bestGuess
                )
            }

            return BookSignature.ReplicateItem(originalSignature, itemCount)
        }

        if (rawSignature.startsWith(LibrarySection.JADE.signatureTag)) {
            val indexSpec = rawSignature.substringAfter(LibrarySection.JADE.signatureTag)
            val (index, subIndex) = parseIndexSpec(indexSpec, "-")

            return BookSignature.JaDe(index, subIndex)
        }

        if (rawSignature.startsWith(LibrarySection.MAGAZINES.signatureTag)) {
            val indexRepresentation = rawSignature.substringAfter(LibrarySection.MAGAZINES.signatureTag)
            val index = indexRepresentation.toIntOrNull()

            if (index == null) {
                val bestIntRepresentation = indexRepresentation.takeWhile { it.isDigit() }.takeIf { it.isNotEmpty() }
                val bestGuess = bestIntRepresentation?.toInt()?.let { BookSignature.Magazine(it) }

                return BookSignature.Faulty(
                    rawSignature,
                    "Part after ${LibrarySection.MAGAZINES.signatureTag} not numeric",
                    bestGuess
                )
            }

            return BookSignature.Magazine(index)
        }

        val signatureMatch = OAS_SIGNATURE_REGEX.matchEntire(rawSignature)
            ?: return BookSignature.Faulty(rawSignature, "Does not follow regular syntax for signatures")

        val (sectionTag, categorySpec, authorTag, indexSpec) = signatureMatch.destructured

        val section = LibrarySection.fromSignatureTag(sectionTag)
            ?: return BookSignature.Faulty(rawSignature, "Unknown section: $sectionTag")

        val (category, subCategories) = parseIndexSpec(categorySpec, "-")
        val (index, subIndex) = parseIndexSpec(indexSpec, "-")

        return BookSignature.Standard(section, category, subCategories, authorTag, index, subIndex)
    }

    fun parseStammdateiFormat(rawSignature: String): BookSignature {
        if (rawSignature.isBlank())
            return BookSignature.Faulty(rawSignature, "Raw signature is blank")

        if (rawSignature.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
            val indexSpec = rawSignature.split("\\s".toRegex()).last()
            val (index, subIndex) = parseIndexSpec(indexSpec, ".")

            return BookSignature.JaDe(index, subIndex)
        }

        val (sectionTag, categorySpec, authorTag, indexSpec) = rawSignature.trim().split("\\s+".toRegex())

        val section = LibrarySection.fromSignatureTag(sectionTag)
            ?: return BookSignature.Faulty(rawSignature, "Unknown section: $sectionTag")

        val (category, subCategory) = parseIndexSpec(categorySpec, ".")
        val (index, subIndex) = parseIndexSpec(indexSpec, ".")

        return BookSignature.Standard(section, category, subCategory, authorTag, index, subIndex)
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