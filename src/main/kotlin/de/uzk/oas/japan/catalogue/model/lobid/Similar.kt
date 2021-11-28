package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Similar(
    val id: String? = null,
    @SerialName("note") val notes: List<String> = emptyList(),
    @SerialName("label") val labels: OneOrMany<String> = OneOrMany.none(),
    val isbn: List<String> = emptyList()
)
