package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
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
