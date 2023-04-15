package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefObject(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") val alternativeLabels: List<String> = emptyList(),
    val inDataset: IdentifiableResource,
    val dateCreated: String? = null,
    val dateModified: String? = null
)
