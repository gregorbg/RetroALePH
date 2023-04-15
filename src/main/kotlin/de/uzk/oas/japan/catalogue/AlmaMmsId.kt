package de.uzk.oas.japan.catalogue

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class AlmaMmsId(override val id: String) : IdProvider
