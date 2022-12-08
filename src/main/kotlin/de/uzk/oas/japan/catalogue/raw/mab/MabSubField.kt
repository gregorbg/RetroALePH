package de.uzk.oas.japan.catalogue.raw.mab

import de.uzk.oas.japan.catalogue.raw.SubField
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("subfield", MabBibRecord.DDB_NAMESPACE, "")
data class MabSubField(
    override val code: String,
    @XmlValue(true) override val content: String
) : SubField
