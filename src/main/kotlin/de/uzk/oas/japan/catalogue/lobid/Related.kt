package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Related(
    override val id: String? = null,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("note") val notes: List<String> = emptyList(),
    val isbn: @Serializable(with = ListWrappingSerializer::class) List<String> = emptyList(),
    val issn: @Serializable(with = ListWrappingSerializer::class) List<String> = emptyList(),
) : JsonLd()
