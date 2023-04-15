package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("type") val types: List<String> = listOf(),
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val gndIdentifier: String? = null,
    val componentList: List<Component> = emptyList(),
    val source: IdentifiableResource? = null,
    val notation: String? = null,
    val version: String? = null,
)
