package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefObject(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    val inDataset: IdentifiableResource,
    val sourceOrganization: IdentifiableResource? = null,
    val provider: IdentifiableResource? = null,
    val modifiedBy: List<IdentifiableResource> = emptyList(),
    val dateCreated: String? = null,
    val dateModified: String? = null
)
