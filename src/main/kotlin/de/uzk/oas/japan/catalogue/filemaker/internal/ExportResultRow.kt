package de.uzk.oas.japan.catalogue.filemaker.internal

import de.uzk.oas.japan.catalogue.filemaker.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("ROW", FileMakerExport.NAMESPACE, "")
data class ExportResultRow(
    @SerialName("MODID") val modId: Int,
    @SerialName("RECORDID") val recordId: Int,
    @SerialName("COL") val columns: List<ExportResultColumn>
)