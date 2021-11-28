package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    val source: IdentifiableResource? = null,
    @SerialName("label") val labels: OneOrMany<String> = OneOrMany.none(),
    @SerialName("altLabel") val alternativeLabels: OneOrMany<String> = OneOrMany.none(),
    val otherTitleInformation: List<String> = emptyList(),
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
    val extent: String? = null,
    @SerialName("alternativeTitle") val alternativeTitles: List<String> = emptyList(),
    val subject: List<Subject> = emptyList(),
    val isbn: List<String> = emptyList(),
    val gndIdentifier: String? = null
)
