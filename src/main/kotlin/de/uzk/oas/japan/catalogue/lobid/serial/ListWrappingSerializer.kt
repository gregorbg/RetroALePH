package de.uzk.oas.japan.catalogue.lobid.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonTransformingSerializer

class ListWrappingSerializer<T>(
    singleValueSerializer: KSerializer<T>
) : JsonTransformingSerializer<List<T>>(ListSerializer(singleValueSerializer)) {
    override fun transformDeserialize(element: JsonElement): JsonArray =
        when (element) {
            is JsonArray -> element
            is JsonNull -> JsonArray(listOf())
            else -> JsonArray(listOf(element))
        }

    override fun transformSerialize(element: JsonElement): JsonElement =
        when (element) {
            is JsonArray -> when (element.size) {
                0 -> JsonNull
                1 -> element.single()
                else -> element
            }
            else -> element
        }
}
