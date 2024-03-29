package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Similar(
    @SerialName("note") val notes: List<String> = emptyList(),
    val isbn: List<String> = emptyList()
)
