package de.uzk.oas.japan.catalogue.lobid.serial

import de.uzk.oas.japan.catalogue.lobid.InstitutionType

object InstitutionTypeCamelCaseSerializer :
    BibTypeCamelCaseSerializer<InstitutionType>(InstitutionType.serializer(), InstitutionType.values())
