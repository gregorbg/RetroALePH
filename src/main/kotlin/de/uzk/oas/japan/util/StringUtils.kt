package de.uzk.oas.japan.util

import com.moji4j.MojiDetector
import org.apache.commons.text.CaseUtils

object StringUtils {
    private val mojiDetector = MojiDetector()

    fun String.enumConventionCamelCase(capitalizeFirstLetter: Boolean = false) =
        CaseUtils.toCamelCase(this, capitalizeFirstLetter, '_')

    val <T : Enum<T>> T.nameCamelCase
        get() = name.enumConventionCamelCase(true)

    val String.isJapanese get() = mojiDetector.hasKana(this) || mojiDetector.hasKanji(this)

    fun String.countOf(char: Char) = count { it == char }

    fun String.cleanAccents(): String = this
        .replace("Ā", "Â")
        .replace("ā", "â")
        .replace("Ē", "Ê")
        .replace("ē", "ê")
        .replace("Ī", "Î")
        .replace("ī", "î")
        .replace("Ō", "Ô")
        .replace("ō", "ô")
        .replace("Ū", "Û")
        .replace("ū", "û")
}