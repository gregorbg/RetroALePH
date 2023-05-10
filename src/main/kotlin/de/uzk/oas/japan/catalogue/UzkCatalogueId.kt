package de.uzk.oas.japan.catalogue

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class UzkCatalogueId(override val id: String) : IdProvider
