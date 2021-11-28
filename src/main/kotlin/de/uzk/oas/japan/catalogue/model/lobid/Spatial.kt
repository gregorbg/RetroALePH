package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spatial(
    val id: String,
    @SerialName("type") val types: List<String>,
    val source: IdentifiableResource,
    @SerialName("label") val labels: OneOrMany<String>,
    val notation: String,
    val focus: Focus
)
