package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    @SerialName("val") val value: String,
    val field: String,
    val term: String,
    @SerialName("norm") val normalizedTerm: String,
)
