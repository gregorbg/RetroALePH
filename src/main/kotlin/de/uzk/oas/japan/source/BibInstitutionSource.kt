package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.catalogue.lobid.BibInstitution

interface BibInstitutionSource {
    fun loadInstitution(institutionIsil: IsilSeal): BibInstitution?
}
