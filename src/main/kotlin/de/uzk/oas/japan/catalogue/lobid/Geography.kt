package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
data class Geography(
    val lat: String,
    val lon: String
)