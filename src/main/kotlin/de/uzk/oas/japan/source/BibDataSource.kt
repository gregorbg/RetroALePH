package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.mab.AlephMab2
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataSource {
    fun loadAlephMab(book: BibliographicResource): AlephMab2?
    fun loadAlmaMarc(book: BibliographicResource): AlmaMarc21?
    fun upcycleToAlmaResource(book: BibliographicResource): BibliographicResource?
}