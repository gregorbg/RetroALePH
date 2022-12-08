package de.uzk.oas.japan.library.signature.data

import de.uzk.oas.japan.library.signature.HierarchicalCategory

enum class LibrarySection(
    val signatureTag: String,
    override vararg val keywordLiterals: String
) : HierarchicalCategory {
    JADE("JaDe"),
    GENERAL("A"),
    ENCYCLOPEDIC("E"),
    GEOGRAPHY("G"),
    HISTORY("Gs"),
    JAPANESE_MEDIA("JMT"),
    ARTS("K"),
    CULTURE_THEORY("Kt"),
    LITERATURE("L"),
    LINGUISTICS("Ls"),
    MUSIC("Mk"),
    SCIENCE("Nt"),
    PHILOSOPHY("P"),
    PEDAGOGY("Pg"),
    POPULAR_CULTURE("Pop"),
    POLITICS_SOCIETY("Ps"),
    LAW("R"),
    RELIGION("Rg"),
    SPORTS("S"),
    ETHNOLOGY("V"),
    ECONOMY("W"),
    MAGAZINES("Z"),
    ERROR("\uD83D\uDEA8");

    override val parent: HierarchicalCategory? = null
    override val signatureIdPart by ::signatureTag

    companion object {
        fun fromSignatureTag(signatureTag: String): LibrarySection? {
            return values().firstOrNull { it.signatureTag.equals(signatureTag, ignoreCase = true) }
        }
    }
}