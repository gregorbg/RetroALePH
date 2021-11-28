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
}
