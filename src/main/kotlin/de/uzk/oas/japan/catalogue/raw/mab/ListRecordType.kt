package de.uzk.oas.japan.catalogue.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("ListRecords", AlephMab2.OAI_PMH_NAMESPACE, "")
data class ListRecordType(
    val records: List<MabRecord>
)
