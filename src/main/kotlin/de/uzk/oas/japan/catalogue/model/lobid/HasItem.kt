package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasItem(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val heldBy: IdentifiableResource? = null,
    @SerialName("note") val notes: List<String> = emptyList(),
    val callNumber: String? = null
)
