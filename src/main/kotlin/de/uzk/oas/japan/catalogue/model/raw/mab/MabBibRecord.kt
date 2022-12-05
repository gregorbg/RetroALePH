package de.uzk.oas.japan.catalogue.model.raw.mab

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("record", MabBibRecord.DDB_NAMESPACE, "")
data class MabBibRecord(
    @XmlElement(true) val leader: String,
    val controlFields: List<MabControlField>,
    val dataFields: List<MabDataField>
) {
    companion object {
        const val DDB_NAMESPACE = "http://www.ddb.de/professionell/mabxml/mabxml-1.xsd"
    }
}
