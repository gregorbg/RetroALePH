package de.uzk.oas.japan.catalogue.model

import kotlinx.serialization.Serializable

@Serializable
data class JapDetailSubject(val keyword: String, val additions: Set<String> = emptySet()) {
    companion object {
        fun parse(raw: String): JapDetailSubject {
            val parts = raw.split("\\s*\\(".toRegex())

            val headKeyword = parts.first()
            val additions = parts.drop(1).map { it.trim().trimEnd(')') }

            return JapDetailSubject(headKeyword, additions.toSet())
        }
    }
}
