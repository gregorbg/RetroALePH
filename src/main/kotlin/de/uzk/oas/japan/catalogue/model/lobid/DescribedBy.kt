package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescribedBy(
    val id: String,
    @SerialName("type") val types: List<String>,
    val modifiedBy: IdentifiableResource? = null,
    val dateCreated: String,
    val dateModified: String? = null,
    val inDataset: IdentifiableResource,
    val resultOf: ResultOf,
    @SerialName("license") val licenses: List<IdentifiableResource>,
    val provider: IdentifiableResource? = null,
    val sourceOrganization: IdentifiableResource? = null,
    val label: String
)
