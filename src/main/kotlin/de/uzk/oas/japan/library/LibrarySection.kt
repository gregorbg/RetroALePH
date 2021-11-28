package de.uzk.oas.japan.library

enum class LibrarySection(val signatureTag: String) {
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
    MAGAZINES("Z");

    companion object {
        fun fromSignatureTag(signatureTag: String): LibrarySection {
            return values().firstOrNull { it.signatureTag.equals(signatureTag, ignoreCase = true) }
                ?: error("Signature tag not found: $signatureTag")
        }
    }
}