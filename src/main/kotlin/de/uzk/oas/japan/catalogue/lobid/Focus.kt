package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Focus(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    val geo: Geography? = null,
)
