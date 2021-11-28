package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Focus(
    val id: String,
    @SerialName("type") val types: List<String>,
    @SerialName("geo") val geography: Geography,
    @SerialName("label") val labels: OneOrMany<String>
)
