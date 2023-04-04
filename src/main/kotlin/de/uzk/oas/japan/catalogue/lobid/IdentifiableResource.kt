package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdentifiableResource(
    override val id: String? = null,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
) : JsonLd()
