package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class TitleField(
    val content: JsonElement, // TODO generic perhaps?
    @SerialName("subfield") val subField: String? = null,
    val mult: Int? = null, // TODO proper name - find out what this is
    val description: String? = null,
    // TODO this should be a separate type, but would require custom-serializing item into multiple field maps depending on key prefix
    // --- too lazy to implement this for current PoC.
    val id: String? = null,
    val type: String? = null, // TODO enum?
    val supplement: String? = null,
)
