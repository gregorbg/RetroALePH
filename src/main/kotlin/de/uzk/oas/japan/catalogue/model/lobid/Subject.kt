package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    override val id: String? = null,
    @SerialName("type") override val types: List<String> = listOf(),
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    val componentList: List<Component> = emptyList(),
    val source: IdentifiableResource? = null,
    val notation: String? = null
) : JsonLd.WeakTyped()