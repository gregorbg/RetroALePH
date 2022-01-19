package de.uzk.oas.japan.catalogue.model

import de.uzk.oas.japan.library.model.JapDetailSubject
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class FreetextSubject(val keyword: String) {
    fun splitDetailSubjects(): List<JapDetailSubject> {
        val splitExceptWithinParens = keyword.split(",\\s*(?![^(]*\\))".toRegex())
        return splitExceptWithinParens.map { JapDetailSubject.parse(it) }
    }
}