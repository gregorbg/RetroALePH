package de.uzk.oas.japan.catalogue.model.raw.mab

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("datafield", MabBibRecord.DDB_NAMESPACE, "")
data class MabDataField(
    val tag: String,
    @SerialName("ind1") val indicatorOne: String,
    @SerialName("ind2") val indicatorTwo: String,
    val subFields: List<MabSubField>
)
