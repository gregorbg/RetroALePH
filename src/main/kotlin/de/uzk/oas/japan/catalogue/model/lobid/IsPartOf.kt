package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IsPartOf(
    @SerialName("type") val types: List<String>,
    val hasSuperordinate: List<IdentifiableResource>,
    val numbering: String? = null
)
