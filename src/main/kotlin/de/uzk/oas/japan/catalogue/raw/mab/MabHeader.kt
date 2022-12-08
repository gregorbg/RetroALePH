package de.uzk.oas.japan.catalogue.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("header", AlephMab2.OAI_PMH_NAMESPACE, "")
data class MabHeader(
    @XmlElement(true) val identifier: String
)
