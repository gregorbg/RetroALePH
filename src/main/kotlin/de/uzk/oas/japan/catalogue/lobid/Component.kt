package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") val alternativeLabels: List<String> = emptyList(),
    val source: IdentifiableResource,
    val gndIdentifier: String? = null,
    //val dateOfBirth: String? = null,
    //val dateOfDeath: String? = null,
    //val dateOfBirthAndDeath: String? = null,
)
