package de.uzk.oas.japan.catalogue.filemaker.internal

import de.uzk.oas.japan.catalogue.filemaker.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("RESULTSET", FileMakerExport.NAMESPACE, "")
data class ExportResultSet(
    @SerialName("FOUND") val found: Int,
    @SerialName("ROW") val rows: List<ExportResultRow>
)