package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescribedBy(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @Serializable(with = ListWrappingSerializer::class) val modifiedBy: List<IdentifiableResource> = emptyList(),
    val dateCreated: String? = null,
    val dateModified: String? = null,
    val inDataset: IdentifiableResource,
    val resultOf: ResultOf,
    @SerialName("license") val licenses: List<IdentifiableResource>,
    val provider: IdentifiableResource? = null,
    val sourceOrganization: IdentifiableResource? = null,
)
