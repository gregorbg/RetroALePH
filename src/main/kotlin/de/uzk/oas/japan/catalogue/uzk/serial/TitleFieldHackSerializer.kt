package de.uzk.oas.japan.catalogue.uzk.serial

import de.uzk.oas.japan.catalogue.uzk.TitleField
import kotlinx.serialization.json.*

object TitleFieldHackSerializer : JsonTransformingSerializer<TitleField>(TitleField.serializer()) {
    const val HACK_ID = "__HACK%"

    override fun transformDeserialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> element
            else -> buildJsonObject {
                put("content", element)
                put("id", JsonPrimitive(HACK_ID))
            }
        }
    }

    override fun transformSerialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> {
                if (element["id"]?.jsonPrimitive?.content == HACK_ID) {
                    return element.getValue("content")
                }

                return element
            }
            else -> element
        }
    }
}