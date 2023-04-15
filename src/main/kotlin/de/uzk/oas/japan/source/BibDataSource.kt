package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.lobid.BibResource
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21

interface BibDataSource {
    fun loadBestand(institutionId: String): List<BibResource>?
    fun loadResource(hbzId: HbzId): BibResource?
    fun loadAlmaMarc(almaMmsId: AlmaMmsId): AlmaMarc21?
}