package de.uzk.oas.japan.catalogue.raw.mab

import de.uzk.oas.japan.catalogue.raw.BibRecord
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("record", MabBibRecord.DDB_NAMESPACE, "")
data class MabBibRecord(
    @XmlElement(true) override val leader: String,
    override val controlFields: List<MabControlField>,
    override val dataFields: List<MabDataField>
) : BibRecord {
    companion object {
        const val DDB_NAMESPACE = "http://www.ddb.de/professionell/mabxml/mabxml-1.xsd"
    }
}
