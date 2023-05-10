package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    @SerialName("hide_detail") val hideDetail: String, // TODO boolean?
    @SerialName("hide_hit") val hideHit: String, // TODO boolean?
)
