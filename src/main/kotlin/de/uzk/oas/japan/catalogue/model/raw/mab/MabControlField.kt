package de.uzk.oas.japan.catalogue.model.raw.mab

import de.uzk.oas.japan.catalogue.model.raw.ControlField
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("controlfield", MabBibRecord.DDB_NAMESPACE, "")
data class MabControlField(
    override val tag: String,
    @XmlValue(true) override val content: String
) : ControlField
