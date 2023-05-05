package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spatial(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    val source: IdentifiableResource,
    val notation: String? = null,
    val focus: Focus? = null,
)
