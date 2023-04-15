package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contribution(
    @SerialName("type") val types: List<String>,
    val agent: Agent,
    val role: IdentifiableResource
)
