package de.uzk.oas.japan.catalogue.model.filemaker

import de.uzk.oas.japan.catalogue.model.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("METADATA", FileMakerExport.NAMESPACE, "")
data class Metadata(
    val foo: Int? = null,
    @SerialName("FIELD") val fields: List<MetadataField>
)
