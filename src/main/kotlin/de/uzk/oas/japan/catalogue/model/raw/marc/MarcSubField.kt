package de.uzk.oas.japan.catalogue.model.raw.marc

import de.uzk.oas.japan.catalogue.model.raw.SubField
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("subfield", "", "")
data class MarcSubField(
    override val code: String,
    @XmlValue(true) override val content: String
) : SubField
