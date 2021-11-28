package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.Serializable

@Serializable
data class IdentifiableResource(
    val id: String? = null,
    val label: String
)
