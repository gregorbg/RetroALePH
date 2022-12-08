package de.uzk.oas.japan.library.signature

import de.uzk.oas.japan.library.signature.data.LibrarySection
import de.uzk.oas.japan.library.signature.data.SignatureSubSection

sealed interface BookSignature {
    val section: LibrarySection
    val index: Int
    val subIndices: List<Int>

    val leafHierarchy: HierarchicalCategory
        get() = section

    fun unwrap(): BookSignature = this

    val signatureTag: String
        get() = "${section.signatureTag}$index-${subIndices.joinToString("-")}$signatureTagDetails"

    val signatureTagDetails: String
        get() = ""

    data class Standard(
        override val section: LibrarySection,
        val category: Int,
        val subCategories: List<Int> = emptyList(),
        val contentTag: String,
        override val index: Int,
        override val subIndices: List<Int> = emptyList()
    ) : BookSignature {
        override val leafHierarchy: HierarchicalCategory
            get() = searchLeafSubSection() ?: super.leafHierarchy

        override val signatureTagDetails: String
            get() = "$contentTag$index-${subIndices.joinToString("-")}".trimEnd('-')

        val hierarchy: List<HierarchicalCategory>
            get() = HierarchicalCategory.computeParentAncestry(leafHierarchy)

        fun searchLeafSubSection(): SignatureSubSection? {
            val searchGroupIds = listOf(category) + subCategories
            return SignatureSubSection.searchTree(searchGroupIds, section) as? SignatureSubSection
        }

        val hierarchicalSignatureTag: String
            get() = hierarchy.joinToString("-") { it.signatureIdPart }
    }

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

    data class ReplicateItem(
        val signature: BookSignature,
        val number: Int
    ) : BookSignature by signature {
        override fun unwrap() = signature.unwrap()
    }

    data class Faulty(
        val rawSignature: String,
        val errorMessage: String,
        val bestGuess: BookSignature? = null
    ) : BookSignature {
        override val section get() = LibrarySection.ERROR
        override val index get() = bestGuess?.index ?: 0
        override val subIndices get() = bestGuess?.subIndices ?: emptyList()

        override val signatureTag: String
            get() = bestGuess?.let { "${section.signatureTag}${it.signatureTag}" }
                ?: "${section.signatureTag}$rawSignature"
    }
}
