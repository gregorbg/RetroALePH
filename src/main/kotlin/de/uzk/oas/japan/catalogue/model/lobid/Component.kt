package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    val source: IdentifiableResource? = null,
    val gndIdentifier: String? = null,
    val dateOfBirth: String? = null,
    val dateOfDeath: String? = null,
    val dateOfBirthAndDeath: String? = null,
) : JsonLd.WeakTyped()
