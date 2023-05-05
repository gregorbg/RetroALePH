package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NatureOfContent(
    val id: String? = null,
    @SerialName("type") val types: List<String> = emptyList(),
    val label: String,
    val source: IdentifiableResource? = null,
    val gndIdentifier: String? = null
)
