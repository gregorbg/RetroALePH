package de.uzk.oas.japan.catalogue.raw.marc.adapter

import de.uzk.oas.japan.catalogue.raw.DataField

data class Marc4JDataField(
    val dataField: org.marc4j.marc.DataField
) : DataField {
    override val tag: String by dataField::tag

    override val indicatorOne: String
        get() = dataField.indicator1.toString()
    override val indicatorTwo: String
        get() = dataField.indicator2.toString()

    override val subFields: List<Marc4JSubField>
        get() = dataField.subfields.map(::Marc4JSubField)
}
