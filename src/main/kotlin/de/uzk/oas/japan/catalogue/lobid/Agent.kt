package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val id: String,
    @SerialName("type") val types: List<String>,
    val label: String,
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    //val otherTitleInformation: List<String> = emptyList(),
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
    //val extent: String? = null,
    //@SerialName("alternativeTitle") val alternativeTitles: List<String> = emptyList(),
    //val subject: List<Subject> = emptyList(),
    //val isbn: List<String> = emptyList(),
    val gndIdentifier: String? = null,
    val sameAs: List<String> = emptyList(),
)
