package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
enum class ResourceType {
    ARCHIVED_WEB_PAGE,
    ARTICLE,
    BIBLIOGRAPHY,
    BIBLIOGRAPHIC_RESOURCE,
    BIOGRAPHY,
    BOOK,
    COLLECTION,
    EDITED_VOLUME,
    FESTSCHRIFT,
    GAME,
    IMAGE,
    JOURNAL,
    LEGISLATION,
    MAP,
    MISCELLANEOUS,
    MULTI_VOLUME_BOOK,
    MUSICAL_RECORDING,
    NEWSPAPER,
    OFFICIAL_PUBLICATION,
    PERIODICAL,
    PROCEEDINGS,
    PUBLICATION_ISSUE,
    PUBLISHED_SCORE,
    REFERENCE_SOURCE,
    REPORT,
    SCHOOLBOOK,
    SERIES,
    STANDARD,
    STATISTICS,
    THESIS,
    // TODO bug?
    SONSTIGES,
}