package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InCollection(
    val id: String,
    @SerialName("type") val types: List<String> = emptyList(),
    @SerialName("label") val labels: OneOrMany<String>
)
