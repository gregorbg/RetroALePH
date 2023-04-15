package de.uzk.oas.japan.catalogue.lobid.serial

import de.uzk.oas.japan.catalogue.lobid.ResourceType

object ResourceTypeCamelCaseSerializer :
    BibTypeCamelCaseSerializer<ResourceType>(ResourceType.serializer(), ResourceType.values())
