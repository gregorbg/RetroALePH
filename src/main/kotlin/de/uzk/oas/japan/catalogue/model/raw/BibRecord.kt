package de.uzk.oas.japan.catalogue.model.raw

interface BibRecord {
    val leader: String
    val controlFields: List<ControlField>
    val dataFields: List<DataField>
}