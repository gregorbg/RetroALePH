package de.uzk.oas.japan.rdaconvert.model

import de.uzk.oas.japan.rdaconvert.model.filemaker.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
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

    @ImplicitReflectionSerializer
    inline fun <reified T : Any> typedRows(): List<T> {
        val mockSerial =
            JSON.stringify(ListSerializer(MapSerializer(String.serializer(), String.serializer())), namedRows())

        return JSON.parse(ListSerializer(T::class.serializer()), mockSerial)
    }

    companion object {
        const val NAMESPACE = "http://www.filemaker.com/fmpxmlresult"

        val JSON = Json(JsonConfiguration.Stable)
    }
}