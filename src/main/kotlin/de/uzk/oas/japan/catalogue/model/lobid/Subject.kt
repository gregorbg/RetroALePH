package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val id: String? = null,
    @SerialName("type") val types: List<String> = emptyList(),
    val componentList: List<Component> = emptyList(),
    val source: IdentifiableResource? = null,
    @SerialName("label") val labels: OneOrMany<String> = OneOrMany.none(),
    val notation: String? = null
)
