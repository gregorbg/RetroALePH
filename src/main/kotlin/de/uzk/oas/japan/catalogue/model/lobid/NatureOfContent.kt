package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NatureOfContent(
    val id: String,
    @SerialName("type") val types: List<String> = emptyList(),
    val source: IdentifiableResource? = null,
    @SerialName("label") val labels: OneOrMany<String>,
    val gndIdentifier: String? = null
)
