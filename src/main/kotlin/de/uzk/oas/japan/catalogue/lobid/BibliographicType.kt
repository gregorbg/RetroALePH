package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
enum class BibliographicType {
    ARTICLE,
    BIBLIOGRAPHY,
    BIOGRAPHY,
    BOOK,
    FESTSCHRIFT,
    IMAGE,
    MAP,
    MISCELLANEOUS,
    MULTI_VOLUME_BOOK,
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
    THESIS,
    GAME
}