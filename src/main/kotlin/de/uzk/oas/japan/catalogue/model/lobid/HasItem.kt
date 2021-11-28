package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasItem(
    val id: String,
    @SerialName("type") val types: List<String>,
    val heldBy: IdentifiableResource? = null,
    @SerialName("note") val notes: List<String> = emptyList(),
    val callNumber: String? = null,
    val label: String
)
