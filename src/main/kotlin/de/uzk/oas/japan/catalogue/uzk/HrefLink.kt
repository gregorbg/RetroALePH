package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class HrefLink(
    val href: String,
    val rel: String, // TODO enum
)