package de.uzk.oas.japan.rdaconvert.model.filemaker

import kotlinx.serialization.Serializable

@Serializable
enum class MetadataEmptyOk {
    YES,
    NO;

    fun toBoolean() = this == YES
}
