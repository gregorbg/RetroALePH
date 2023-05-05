package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Instrument(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
)
