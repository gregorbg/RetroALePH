package de.uzk.oas.japan.catalogue.uzk.serial

import de.uzk.oas.japan.catalogue.uzk.Facet
import kotlinx.serialization.json.*

object FacetsSerializer : JsonTransformingSerializer<Facet>(Facet.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> {
                val straightForward = element.filterKeys { !it.startsWith("custom") }
                val custom = element - straightForward.keys

                val customList = custom.entries
                    .sortedBy { it.key.substringAfter("custom").toInt().minus(1) }
                    .map { it.value }

                val decodeValues = straightForward + ("custom" to JsonArray(customList))

                JsonObject(decodeValues)
            }
            else -> element
        }
    }

    override fun transformSerialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> {
                val straightForward = element.toMap() - "custom"
                val custom = element.getValue("custom").jsonArray

                val customMap = custom.withIndex().associate { "custom${it.index + 1}" to it.value }

                val encodeValues = straightForward + customMap

                JsonObject(encodeValues)
            }
            else -> element
        }
    }
}