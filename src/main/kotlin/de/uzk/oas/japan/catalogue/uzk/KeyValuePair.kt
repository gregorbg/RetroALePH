package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class KeyValuePair(
    val key: String,
    val value: Int
)
