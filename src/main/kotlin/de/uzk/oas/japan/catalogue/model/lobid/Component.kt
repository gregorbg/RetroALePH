package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    val source: IdentifiableResource? = null,
    @SerialName("label") val labels: OneOrMany<String>,
    @SerialName("altLabel") val alternativeLabels: OneOrMany<String> = OneOrMany.none(),
    val gndIdentifier: String? = null,
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
)
