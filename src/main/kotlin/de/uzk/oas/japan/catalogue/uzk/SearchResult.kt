package de.uzk.oas.japan.catalogue.uzk

import de.uzk.oas.japan.catalogue.uzk.serial.FacetsSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val filter: List<Filter> = emptyList(),
    val link: HrefLink,
    val records: List<CatalogueTitle>,
    val facets: @Serializable(with = FacetsSerializer::class) Facet,
    @SerialName("meta") val metadata: SearchMetadata,
)
