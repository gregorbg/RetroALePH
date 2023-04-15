package de.uzk.oas.japan.catalogue

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class IsilSeal(override val id: String) : IdProvider
