package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NatureOfContent(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val source: IdentifiableResource? = null,
    val gndIdentifier: String? = null
)
