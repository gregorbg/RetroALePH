package de.uzk.oas.japan.catalogue.raw.marc.adapter

import de.uzk.oas.japan.catalogue.raw.SubField

data class Marc4JSubField(
    val subField: org.marc4j.marc.Subfield
) : SubField {
    override val code: String
        get() = subField.code.toString()

    override val content: String by subField::data
}
