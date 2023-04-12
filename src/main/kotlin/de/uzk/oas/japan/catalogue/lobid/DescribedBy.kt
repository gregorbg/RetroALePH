package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescribedBy(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) val modifiedBy: List<IdentifiableResource> = emptyList(),
    val dateCreated: String? = null, // FIXME require between here and RefObject
    val dateModified: String? = null,
    val inDataset: IdentifiableResource,
    val resultOf: ResultOf,
    @SerialName("license") val licenses: List<IdentifiableResource>,
    val provider: IdentifiableResource? = null,
    val sourceOrganization: IdentifiableResource? = null,
) : JsonLd.WeakTyped()
