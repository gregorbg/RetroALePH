package de.uzk.oas.japan.catalogue.model.filemaker

import kotlinx.serialization.Serializable

@Serializable
enum class MetadataFieldType {
    TEXT,
    NUMBER,
    DATE
}