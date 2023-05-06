package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.catalogue.lobid.BibResource
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataStorage : BibDataSource {
    fun storeBestand(institutionIsil: IsilSeal, lobidBestand: List<BibResource>)
    fun storeResource(bookId: AlmaMmsId, book: BibResource)
    fun storeAlmaMarc(bookId: AlmaMmsId, marc: AlmaMarc21)
}
