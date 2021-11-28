package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefObject(
    val id: String,
    @SerialName("type") val types: List<String>,
    val inDataset: IdentifiableResource,
    val label: String
)
