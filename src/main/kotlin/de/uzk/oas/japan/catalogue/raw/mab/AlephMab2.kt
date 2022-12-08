package de.uzk.oas.japan.catalogue.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("OAI-PMH", AlephMab2.OAI_PMH_NAMESPACE, "")
data class AlephMab2(
    val recordList: ListRecordType
) {
    companion object {
        const val OAI_PMH_NAMESPACE = "http://www.openarchives.org/OAI/2.0/"
    }
}
