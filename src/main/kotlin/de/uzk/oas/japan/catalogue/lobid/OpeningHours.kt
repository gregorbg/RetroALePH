package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpeningHours(
    @SerialName("type") @Serializable(with = ListWrappingSerializer::class) val types: List<String>,
    val comment: String,
    val description: String,
)
