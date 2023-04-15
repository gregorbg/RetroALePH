package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Related( // TODO keine dokumentierte Referenz. Kaputtmachen um zu sehen, was klappt
    val id: String,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
    @SerialName("note") val notes: List<String> = emptyList(),
    val isbn: @Serializable(with = ListWrappingSerializer::class) List<String> = emptyList(),
    val issn: @Serializable(with = ListWrappingSerializer::class) List<String> = emptyList(),
)
