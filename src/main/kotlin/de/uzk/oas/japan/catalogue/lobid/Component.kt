package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val id: String? = null, // Freie Verschlagwortung has no GND IDs or such
    @SerialName("type") val types: List<String> = emptyList(), // baumelt da so leer rum: HT007306874
    val label: String,
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val source: IdentifiableResource? = null,
    val gndIdentifier: String? = null,
    // A subject component can be a person
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
)
