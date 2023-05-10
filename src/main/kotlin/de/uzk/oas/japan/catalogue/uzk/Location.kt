package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val description: String,
    val link: HrefLink,
    val id: String,
)
