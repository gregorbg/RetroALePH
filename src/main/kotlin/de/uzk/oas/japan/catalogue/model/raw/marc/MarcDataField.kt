package de.uzk.oas.japan.catalogue.model.raw.marc

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("datafield", "", "")
data class MarcDataField(
    val tag: String,
    @SerialName("ind1") val indicatorOne: String,
    @SerialName("ind2") val indicatorTwo: String,
    val subFields: List<MarcSubField>
)
