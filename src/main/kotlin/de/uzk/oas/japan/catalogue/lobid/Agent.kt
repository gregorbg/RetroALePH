package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    val source: IdentifiableResource? = null,
    val otherTitleInformation: List<String> = emptyList(),
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
    val extent: String? = null,
    @SerialName("alternativeTitle") val alternativeTitles: List<String> = emptyList(),
    val subject: List<Subject> = emptyList(),
    val isbn: List<String> = emptyList(),
    val gndIdentifier: String? = null
) : JsonLd.WeakTyped()
