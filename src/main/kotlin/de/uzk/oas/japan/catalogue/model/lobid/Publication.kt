package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    @SerialName("type") val types: List<String>,
    @SerialName("description") val descriptions: List<String> = emptyList(),
    val frequency: List<IdentifiableResource> = emptyList(),
    @SerialName("note") val notes: List<String> = emptyList(),
    val publicationHistory: String? = null,
    val location: OneOrMany<String> = OneOrMany.none(),
    val publishedBy: OneOrMany<String> = OneOrMany.none(),
    val startDate: String? = null,
    val endDate: String? = null
)
