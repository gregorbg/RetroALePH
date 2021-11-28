package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.Serializable

@Serializable
data class Geography(
    val lat: String,
    val lon: String
)
