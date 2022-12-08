package de.uzk.oas.japan.catalogue.raw

interface BibRecord {
    val leader: String
    val controlFields: List<ControlField>
    val dataFields: List<DataField>
}