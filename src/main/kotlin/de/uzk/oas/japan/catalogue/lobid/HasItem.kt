package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasItem(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") val alternativeLabels: List<String> = emptyList(),
    val heldBy: HeldBy,
    @SerialName("note") val notes: List<String> = emptyList(),
    val callNumber: String? = null,
    val serialNumber: String? = null,
    val currentLocation: String? = null,
    val electronicLocator: String? = null,
    @SerialName("sublocation") val subLocation: String? = null,
)
