package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collects(
    val type: String,
    val extent: IntlLabelResource,
    @SerialName("subject") val subjects: List<String>
)
