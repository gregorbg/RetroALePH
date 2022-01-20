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

    fun filterReasonableComponentLiterals(): Set<String> {
        val reasonableAdditions = additions.filter { it.isReasonableAddition() }
        val completeValues = reasonableAdditions + keyword

        return completeValues.map { it.cleanAccents() }
            .filterNot { REGEX_AT_LEAST_THREE_DIGITS.containsMatchIn(it) }
            .toSet()
    }

    companion object {
        val REGEX_AT_LEAST_THREE_DIGITS = "\\d{3,}".toRegex()

        fun String.isReasonableAddition(): Boolean = ',' !in this && '-' !in this && split(" ").size <= 3

        fun parse(raw: String): JapDetailSubject {
            val parts = raw.split("\\s*\\(".toRegex())

            val headKeyword = parts.first()
            val additions = parts.drop(1).map { it.trim().trimEnd(')') }

            return JapDetailSubject(headKeyword, additions.toSet())
        }
    }
}
