package de.uzk.oas.japan.catalogue.uzk.serial

import de.uzk.oas.japan.catalogue.uzk.Item
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.jsonObject

object ItemSerializer : JsonTransformingSerializer<Item>(Item.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> {
                val id = element.getValue("id")
                val fields = element.filterKeys { it != "id" }

                val content = mapOf(
                    "id" to id,
                    "fields" to JsonObject(fields)
                )

                JsonObject(content)
            }
            else -> element
        }
    }

    override fun transformSerialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> {
                val fields = element.getValue("fields").jsonObject.toMap()
                val id = element.getValue("id")

                val rawFields = fields + ("id" to id)

                JsonObject(rawFields)
            }
            else -> element
        }
    }
}
