package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val id: String? = null, // When a person/institution has no GND entry linked
    @SerialName("type") val types: List<String>,
    val label: String? = null, // FIXME cleanup wann darf das null sein?
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
    val source: IdentifiableResource? = null,
    val gndIdentifier: String? = null,
    val sameAs: List<String> = emptyList(),
)
