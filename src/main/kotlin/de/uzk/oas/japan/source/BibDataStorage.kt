package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataStorage : BibDataSource {
    fun storeBestand(institutionId: String, lobidBestand: List<BibliographicResource>)
    fun storeResource(bookId: HbzId, book: BibliographicResource)
    fun storeAlmaMarc(bookId: AlmaMmsId, marc: AlmaMarc21)
}
