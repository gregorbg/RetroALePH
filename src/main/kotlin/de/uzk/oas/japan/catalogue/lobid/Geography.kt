package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geography(
    @SerialName("lat") val latitude: String,
    @SerialName("lon") val longitude: String,
)
