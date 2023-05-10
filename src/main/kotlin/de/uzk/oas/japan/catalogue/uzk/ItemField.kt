package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ItemField(
    val content: JsonElement? = null, // TODO generic perhaps? what about empty X3330?
    @SerialName("subfield") val subField: String? = null,
    val mult: Int? = null, // TODO proper name - figure out what this does
)