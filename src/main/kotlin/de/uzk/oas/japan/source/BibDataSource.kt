package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.lobid.BibliographicResource
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataSource {
    fun loadBestand(institutionId: String): List<BibliographicResource>?
    fun loadResource(hbzId: HbzId): BibliographicResource?
    fun loadAlmaMarc(almaMmsId: AlmaMmsId): AlmaMarc21?
}