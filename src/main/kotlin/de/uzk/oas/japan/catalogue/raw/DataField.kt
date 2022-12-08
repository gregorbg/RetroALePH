package de.uzk.oas.japan.catalogue.raw

interface DataField {
    val tag: String
    val indicatorOne: String
    val indicatorTwo: String
    val subFields: List<SubField>
}