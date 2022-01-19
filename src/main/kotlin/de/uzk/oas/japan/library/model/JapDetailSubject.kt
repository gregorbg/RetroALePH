package de.uzk.oas.japan.library.model

import de.uzk.oas.japan.util.StringUtils.cleanAccents
import kotlinx.serialization.Serializable

@Serializable
data class JapDetailSubject(val keyword: String, val additions: Set<String> = emptySet()) {
    fun filterComponentLiterals(): Set<String> {
        val completeValues = additions + keyword

        return completeValues.map { it.cleanAccents() }
            .toSet()
    }

    companion object {
        fun String.isReasonableAddition(): Boolean = ',' !in this && '-' !in this && split(" ").size <= 3

        fun parse(raw: String): JapDetailSubject {
            val parts = raw.split("\\s*\\(".toRegex())

            val headKeyword = parts.first()
            val additions = parts.drop(1).map { it.trim().trimEnd(')') }

            return JapDetailSubject(headKeyword, additions.toSet())
        }
    }
}
