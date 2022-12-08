package de.uzk.oas.japan.catalogue.raw.marc

import de.uzk.oas.japan.catalogue.raw.ControlField
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("controlfield", "", "")
data class MarcControlField(
    override val tag: String,
    @XmlValue(true) override val content: String
) : ControlField
