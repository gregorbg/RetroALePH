package de.uzk.oas.japan.catalogue.model.lobid.serial

import de.uzk.oas.japan.catalogue.model.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.model.lobid.BibliographicType
import de.uzk.oas.japan.util.StringUtils.enumConventionCamelCase
import de.uzk.oas.japan.util.StringUtils.nameCamelCase
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.*

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

private fun String.toJsonString() = JsonPrimitive(this)
private fun <T : JsonElement> List<T>.toJsonArray() = JsonArray(this)
