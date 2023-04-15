package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Publication( // TODO einige default values besser weg?
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    @SerialName("description") val descriptions: List<String> = emptyList(),
    val frequency: List<IdentifiableResource> = emptyList(),
    @SerialName("note") val notes: List<String> = emptyList(),
    val publicationHistory: String? = null,
    @Serializable(with = ListWrappingSerializer::class) val location: List<String> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) val publishedBy: List<String> = emptyList(),
    val startDate: String? = null,
    val endDate: String? = null
)
