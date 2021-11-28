package de.uzk.oas.japan.catalogue.model.filemaker

import de.uzk.oas.japan.catalogue.model.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("DATABASE", FileMakerExport.NAMESPACE, "")
data class DatabaseProperties(
    @SerialName("DATEFORMAT") val dateFormat: String,
    @SerialName("LAYOUT") val layout: String,
    @SerialName("NAME") val name: String,
    @SerialName("RECORDS") val records: Int,
    @SerialName("TIMEFORMAT") val timeFormat: String
)
