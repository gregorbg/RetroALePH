package de.uzk.oas.japan.catalogue.filemaker.internal

import kotlinx.serialization.Serializable

@Serializable
enum class MetadataFieldType {
    TEXT,
    NUMBER,
    DATE
}