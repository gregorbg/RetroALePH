package de.uzk.oas.japan.catalogue.raw.marc.adapter

import de.uzk.oas.japan.catalogue.raw.BibRecord
import de.uzk.oas.japan.catalogue.raw.marc.AlmaMarc21
import nl.adaptivity.xmlutil.serialization.XML
import org.marc4j.MarcXmlReader

data class Marc4JRecord(
    val record: org.marc4j.marc.Record
) : BibRecord {
    override val leader: String = record.leader.marshal()

    override val controlFields: List<Marc4JControlField> = record.controlFields.map(::Marc4JControlField)
    override val dataFields: List<Marc4JDataField> = record.dataFields.map(::Marc4JDataField)

    companion object {
        fun fromAlmaRecord(record: AlmaMarc21): Marc4JRecord {
            val serial = XML.encodeToString(record)
            val marcXmlReader = MarcXmlReader(serial.byteInputStream())

            val record4J = marcXmlReader.next()

            return Marc4JRecord(record4J)
        }
    }
}
