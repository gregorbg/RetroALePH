package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefObject(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val inDataset: IdentifiableResource,
)
