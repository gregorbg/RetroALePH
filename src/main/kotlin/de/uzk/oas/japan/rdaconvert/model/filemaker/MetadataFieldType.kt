package de.uzk.oas.japan.rdaconvert.model.filemaker

import kotlinx.serialization.Serializable

@Serializable
enum class MetadataFieldType {
    TEXT,
    NUMBER,
    DATE
}