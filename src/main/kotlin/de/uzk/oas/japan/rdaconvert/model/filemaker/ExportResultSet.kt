package de.uzk.oas.japan.rdaconvert.model.filemaker

import de.uzk.oas.japan.rdaconvert.model.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("RESULTSET", FileMakerExport.NAMESPACE, "")
data class ExportResultSet(
    @SerialName("FOUND") val found: Int,
    @SerialName("ROW") val rows: List<ExportResultRow>
)