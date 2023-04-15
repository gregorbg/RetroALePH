package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultOf(
    @SerialName("type") val types: List<String>,
    val endTime: String,
    val instrument: Instrument,
    @SerialName("object") val refObject: RefObject
)
