package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val id: String? = null,
    @SerialName("type") val types: List<String> = listOf(),
    val label: String? = null,
    val gndIdentifier: String? = null,
    val componentList: List<Component> = emptyList(),
    val source: IdentifiableResource? = null,
    val notation: String? = null,
    val version: String? = null,
)
