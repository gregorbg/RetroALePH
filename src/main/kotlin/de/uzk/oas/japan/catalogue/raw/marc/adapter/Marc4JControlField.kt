package de.uzk.oas.japan.catalogue.raw.marc.adapter

import de.uzk.oas.japan.catalogue.raw.ControlField

data class Marc4JControlField(
    val controlField: org.marc4j.marc.ControlField
): ControlField {
    override val tag: String by controlField::tag
    override val content: String by controlField::data
}
