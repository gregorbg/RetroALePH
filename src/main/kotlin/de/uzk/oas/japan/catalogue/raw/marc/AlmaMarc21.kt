package de.uzk.oas.japan.catalogue.raw.marc

import de.uzk.oas.japan.catalogue.raw.BibRecord
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("record", "", "")
data class AlmaMarc21(
    @XmlElement(true) override val leader: String,
    override val controlFields: List<MarcControlField>,
    override val dataFields: List<MarcDataField>
) : BibRecord
