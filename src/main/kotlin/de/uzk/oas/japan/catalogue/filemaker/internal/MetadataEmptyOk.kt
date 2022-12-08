package de.uzk.oas.japan.catalogue.filemaker.internal

import kotlinx.serialization.Serializable

@Serializable
enum class MetadataEmptyOk {
    YES,
    NO;

    fun toBoolean() = this == YES
}
