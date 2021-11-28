package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Related(
    val id: String,
    val label: String,
    @SerialName("note") val notes: List<String> = emptyList()
)
