package de.uzk.oas.japan.rdaconvert.model.filemaker

import de.uzk.oas.japan.rdaconvert.model.FileMakerExport
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