package de.uzk.oas.japan.catalogue.lobid.serial

import de.uzk.oas.japan.util.StringUtils.enumConventionCamelCase
import de.uzk.oas.japan.util.StringUtils.nameCamelCase
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*

abstract class BibTypeCamelCaseSerializer<E : Enum<E>>(tSerializer: KSerializer<E>, enumValues: Array<E>) :
    JsonTransformingSerializer<E>(tSerializer) {
    private val lookupMap = enumValues.associateBy { it.nameCamelCase }

    override fun transformDeserialize(element: JsonElement) =
        when (element) {
            is JsonPrimitive -> lookupMap.getValue(element.content).name.toJsonString()
            else -> element
        }

    override fun transformSerialize(element: JsonElement) =
        when (element) {
            is JsonPrimitive -> element.content.enumConventionCamelCase(true).toJsonString()
            else -> element
        }

    private fun String.toJsonString() = JsonPrimitive(this)
}
