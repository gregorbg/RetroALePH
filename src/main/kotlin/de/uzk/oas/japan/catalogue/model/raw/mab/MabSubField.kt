package de.uzk.oas.japan.catalogue.model.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("subfield", MabBibRecord.DDB_NAMESPACE, "")
data class MabSubField(
    val code: String,
    @XmlValue(true) val content: String
)
