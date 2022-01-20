package de.uzk.oas.japan.library.model

interface HierarchicalCategory {
    val parent: HierarchicalCategory?

    val keywordLiterals: Array<out String>

    val signatureIdPart: String
}
