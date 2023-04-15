package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleOfWork(
    override val id: String? = null,
    @SerialName("type") override val types: List<String> = emptyList(),
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    val creatorOfWork: String? = null,
    val workNumbering: String? = null,
    val instrumentation: List<String> = emptyList(),
) : JsonLd.WeakTyped()
