package de.uzk.oas.japan.catalogue.model.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("metadata", AlephMab2.OAI_PMH_NAMESPACE, "")
data class MabMetadata(
    val records: List<MabBibRecord>
)
