package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasItem(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    val heldBy: IdentifiableResource? = null,
    @SerialName("note") val notes: List<String> = emptyList(),
    val callNumber: String? = null
) : JsonLd.WeakTyped()
