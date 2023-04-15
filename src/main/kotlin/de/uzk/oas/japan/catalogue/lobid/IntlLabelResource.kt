package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntlLabelResource(
    val id: String,
    @SerialName("type") @Serializable(with = ListWrappingSerializer::class) val types: List<String> = emptyList(),
    val intlLabels: Map<String, String> = emptyMap(),
)
