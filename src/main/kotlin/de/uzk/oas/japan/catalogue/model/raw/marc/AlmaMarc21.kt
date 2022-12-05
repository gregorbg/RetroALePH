package de.uzk.oas.japan.catalogue.model.raw.marc

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("record", "", "")
data class AlmaMarc21(
    @XmlElement(true) val leader: String,
    val controlFields: List<MarcControlField>,
    val dataFields: List<MarcDataField>
)
