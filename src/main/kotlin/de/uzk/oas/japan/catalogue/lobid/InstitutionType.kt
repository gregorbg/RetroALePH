package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
enum class InstitutionType {
    LIBRARY,
    ARCHIVE,
    MUSEUM,
    PUBLISHER,
    ORGANIZATION,
}