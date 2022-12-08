package de.uzk.oas.japan.library.subject

import de.uzk.oas.japan.util.StringUtils.countOf

enum class SubjectKeywordError(val description: String, val check: (String) -> Boolean) {
    PIPE_BAR_LITERAL("Enthält das Zeichen |", { '|' in it }),
    ASTERISK_LITERAL("Enthält das Zeichen *", { '*' in it }),
    COLON_LITERAL("Enthält das Zeichen :", { ':' in it }),
    SEMICOLON_LITERAL("Enthält das Zeichen ;", { ';' in it }),
    FULLSTOP_LITERAL("Enthält das Zeichen .", { '.' in it }),
    AMPERSAND_LITERAL("Enthält das Zeichen &", { '&' in it }),
    TILDE_LITERAL("Enthält das Zeichen ~", { '〜' in it || '~' in it }),
    JAPANESE_DASH_LITERAL("Enthält einen japanischen Bindestrich oder Längungsstrich", { '–' in it || 'ー' in it }),
    SINGLE_DASH_SPACE_LITERAL("Enthält einen Gedankenstrich mit Leerzeichen", { " - " in it }),
    DOUBLE_DASH_LITERAL("Enthält die Zeichenfolge --", { "--" in it }),
    SQUARE_BRACKETS("Enthält eckige Klammern (\"[\" oder \"]\")", { '[' in it || ']' in it }),
    UNMATCHED_PARENTHESES(
        "Enthält eine falsche Anzahl von Klammern (weniger \"(\" als \")\" oder umgekehrt)",
        { it.countOf('(') != it.countOf(')') }),
    ASYMMETRIC_PARENTHESES("Enthält Klammern in der falschen Reihenfolge", { it.indexOf(')') < it.indexOf('(') }),
    PARENTHESES_WITHOUT_SPACE("Enthält Klammern ohne Leerzeichen", { "\\S\\(".toRegex().containsMatchIn(it) || "\\)[^, ]".toRegex().containsMatchIn(it) }),
    PARENTHESES_WITH_SUPERFLUOUS_SPACE("Enthält Klammern mit überflüssigen Leerzeichen", { "( " in it || " )" in it }),
    COMMA_SYNTAX_BROKEN("Enthält falsch gesetzte Kommata", { " ," in it }),
    ENDS_WITH_COMMA("Endet mit einem Komma", { it.trim().endsWith(',') }),
    LITERAL_ENUMERATION_USING_UND("Enthält eine Aufzählung mittels \"und\"", { " und " in it }),
    MULTIPLE_PARENTHESES_WITHOUT_SPACE("Enthält mehrere Klammern ohne Leerzeichen", { ")(" in it }),
    IS_ISBN("Ist eine ISBN (Heuristik: Beginnt mit \"4-\")", { it.trim().startsWith("4-") }),
    IS_MEASUREMENT("Ist eine Maßangabe (Heuristik: Nur Zahlen gefolgt von Buchstaben)", { it.matches("^\\d+\\s*[A-Za-z]+$".toRegex()) }),
    CONTAINS_ONLY_NUMBERS_AND_SYMBOLS("Enthält nur nicht-alphabetische Zeichen", { it.matches("[^A-Za-z]+".toRegex()) }),
    POTENTIAL_CAPS_LOCK("Potenziell Caps-Lock (Heuristik: 5 oder mehr Großbuchstaben)", { "[A-Z]{5,}".toRegex().containsMatchIn(it) })

    // contains a word that also contains a number
    // contains multiple spaces
}