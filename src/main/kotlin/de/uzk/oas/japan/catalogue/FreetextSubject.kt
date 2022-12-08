package de.uzk.oas.japan.catalogue

import de.uzk.oas.japan.library.subject.JapDetailSubject
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class FreetextSubject(val keyword: String) {
    fun splitDetailSubjects(): List<JapDetailSubject> {
        val splitExceptWithinParens = keyword.split(COMMA_EXCEPT_IN_PARENS_REGEX)
        return splitExceptWithinParens.map { JapDetailSubject.parse(it) }
    }

    companion object {
        val COMMA_EXCEPT_IN_PARENS_REGEX = ",\\s*(?![^(]*\\))".toRegex()
    }
}