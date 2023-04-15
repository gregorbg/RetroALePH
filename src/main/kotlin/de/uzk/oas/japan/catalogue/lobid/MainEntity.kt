package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
data class MainEntity(
    val id: String,
    val dateCreated: String? = null,
    val dateModified: String? = null,
)
