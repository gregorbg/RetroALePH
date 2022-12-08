package de.uzk.oas.japan.catalogue.filemaker.internal

import de.uzk.oas.japan.catalogue.filemaker.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("FIELD", FileMakerExport.NAMESPACE, "")
data class MetadataField(
    @XmlElement(false) @SerialName("EMPTYOK") val emptyOk: MetadataEmptyOk,
    @SerialName("MAXREPEAT") val maxRepeat: Int,
    @SerialName("NAME") val name: String,
    @XmlElement(false) @SerialName("TYPE") val type: MetadataFieldType
)