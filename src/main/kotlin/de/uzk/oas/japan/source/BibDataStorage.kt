package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.mab.AlephMab2
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataStorage : BibDataSource {
    val cachedBestand: List<BibliographicResource>?

    fun cacheBestand(lobidBestand: List<BibliographicResource>)

    fun cacheAlephMab(book: BibliographicResource, mab: AlephMab2)
    fun cacheAlmaMarc(book: BibliographicResource, marc: AlmaMarc21)
    fun cacheAlmaResource(book: BibliographicResource)
}
