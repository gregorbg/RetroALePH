package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class AvailabilityInfo(
    val service: String,
    val content: String,
)
