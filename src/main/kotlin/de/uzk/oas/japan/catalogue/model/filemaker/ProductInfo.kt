package de.uzk.oas.japan.catalogue.model.filemaker

import de.uzk.oas.japan.catalogue.model.FileMakerExport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("PRODUCT", FileMakerExport.NAMESPACE, "")
data class ProductInfo(
    @SerialName("BUILD") val build: String,
    @SerialName("NAME") val name: String,
    @SerialName("VERSION") val version: String
)
