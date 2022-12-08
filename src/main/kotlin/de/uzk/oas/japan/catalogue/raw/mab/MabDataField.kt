package de.uzk.oas.japan.catalogue.raw.mab

import de.uzk.oas.japan.catalogue.raw.DataField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("datafield", MabBibRecord.DDB_NAMESPACE, "")
data class MabDataField(
    override val tag: String,
    @SerialName("ind1") override val indicatorOne: String,
    @SerialName("ind2") override val indicatorTwo: String,
    override val subFields: List<MabSubField>
) : DataField
