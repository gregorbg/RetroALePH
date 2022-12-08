package de.uzk.oas.japan.catalogue.filemaker.internal

import de.uzk.oas.japan.catalogue.filemaker.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("COL", FileMakerExport.NAMESPACE, "")
data class ExportResultColumn(
    @SerialName("DATA") @XmlElement(true) val data: String
)
