package de.uzk.oas.japan.catalogue.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("record", AlephMab2.OAI_PMH_NAMESPACE, "")
data class MabRecord(
    val header: MabHeader,
    val metadata: MabMetadata
)
