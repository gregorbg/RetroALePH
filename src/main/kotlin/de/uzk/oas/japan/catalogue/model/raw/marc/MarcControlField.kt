package de.uzk.oas.japan.catalogue.model.raw.marc

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("controlfield", "", "")
data class MarcControlField(
    val tag: String,
    @XmlValue(true) val content: String
)
