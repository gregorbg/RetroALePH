package de.uzk.oas.japan.catalogue.uzk.serial

import de.uzk.oas.japan.catalogue.uzk.KeyValuePair
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

object KeyValueArraySerializer : JsonTransformingSerializer<KeyValuePair>(KeyValuePair.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonArray -> JsonObject(mapOf("key" to element[0], "value" to element[1]))
            else -> element
        }
    }

    override fun transformSerialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> JsonArray(listOfNotNull(element["key"], element["value"]))
            else -> element
        }
    }
}