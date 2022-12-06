package de.uzk.oas.japan.catalogue.model.raw.marc

import de.uzk.oas.japan.catalogue.model.raw.DataField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("datafield", "", "")
data class MarcDataField(
    override val tag: String,
    @SerialName("ind1") override val indicatorOne: String,
    @SerialName("ind2") override val indicatorTwo: String,
    override val subFields: List<MarcSubField>
) : DataField
