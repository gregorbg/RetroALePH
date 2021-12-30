package de.uzk.oas.japan.catalogue.model.lobid.serial

import de.uzk.oas.japan.catalogue.model.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.model.lobid.BibliographicType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.*
import org.apache.commons.text.CaseUtils

object BibliographicTypeCamelCaseSerializer :
    JsonTransformingSerializer<BibliographicType>(BibliographicType.serializer()) {
    private val lookupMap = BibliographicType.values()
        .associateBy { it.nameCamelCase }

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

    private fun String.enumConventionCamelCase(capitalizeFirstLetter: Boolean = false) =
        CaseUtils.toCamelCase(this, capitalizeFirstLetter, '_')

    private val <T : Enum<T>> T.nameCamelCase
        get() = name.enumConventionCamelCase(true)
}

object BibliographicTypeListFilterSerializer :
    JsonTransformingSerializer<List<BibliographicType>>(ListSerializer(BibliographicTypeCamelCaseSerializer)) {
    private val filterSuperType = BibliographicResource::class.simpleName

    override fun transformDeserialize(element: JsonElement) =
        when (element) {
            is JsonArray -> element.filter { it.jsonPrimitive.content != filterSuperType }
                .toJsonArray()
            else -> element
        }

    override fun transformSerialize(element: JsonElement) =
        when (element) {
            is JsonArray -> (element + JsonPrimitive(filterSuperType)).toJsonArray()
            else -> element
        }
}

private fun String.toJsonString(): JsonPrimitive = JsonPrimitive(this)
private fun <T : JsonElement> List<T>.toJsonArray() = JsonArray(this)
