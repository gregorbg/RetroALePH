package de.uzk.oas.japan.catalogue.model.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("controlfield", MabBibRecord.DDB_NAMESPACE, "")
data class MabControlField(
    val tag: String,
    @XmlValue(true) val content: String
)
