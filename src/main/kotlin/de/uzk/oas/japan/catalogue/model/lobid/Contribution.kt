package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contribution(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    val agent: Agent,
    val role: IdentifiableResource,
    @SerialName("label") val labels: OneOrMany<String> = OneOrMany.none()
)
