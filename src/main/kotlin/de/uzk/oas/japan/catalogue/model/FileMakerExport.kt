package de.uzk.oas.japan.catalogue.model

import de.uzk.oas.japan.catalogue.model.filemaker.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("FMPXMLRESULT", FileMakerExport.NAMESPACE, "")
data class FileMakerExport(
    @XmlElement(true) @SerialName("ERRORCODE") val errorCode: Int,
    val product: ProductInfo,
    val database: DatabaseProperties,
    val metadata: Metadata,
    val resultSet: ExportResultSet
) {
    fun namedRows(): List<Map<String, String>> {
        return resultSet.rows.map {
            val keys = metadata.fields.map(MetadataField::name)
            val values = it.columns.map(ExportResultColumn::data)

            keys.zip(values).toMap()
        }
    }

    inline fun <reified T : Any> typedRows(): List<T> {
        val mockSerial = Json.encodeToString(namedRows())
        return Json.decodeFromString(mockSerial)
    }

    companion object {
        const val NAMESPACE = "http://www.filemaker.com/fmpxmlresult"
    }
}