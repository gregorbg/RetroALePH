package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.IsilSeal
import kotlinx.serialization.Serializable

@Serializable
data class HeldBy(
    val id: String,
    val label: String,
    val isil: IsilSeal? = null
)
