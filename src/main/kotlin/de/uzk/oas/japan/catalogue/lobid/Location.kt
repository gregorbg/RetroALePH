package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("type") @Serializable(with = ListWrappingSerializer::class) val types: List<String>,
    val description: String? = null,
    val address: Address,
    @SerialName("openingHoursSpecification") val openingHours: OpeningHours? = null,
    @SerialName("geo") val geography: Geography,
)
