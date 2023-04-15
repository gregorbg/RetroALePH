package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleOfWork( // TODO keine dokumentierte Referenz. Kaputtmachen um zu sehen, was klappt
    @SerialName("type") val types: List<String> = emptyList(),
    val label: String,
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) val alternativeLabels: List<String> = emptyList(),
    val creatorOfWork: String? = null,
    val workNumbering: String? = null,
    val instrumentation: List<String> = emptyList(),
)
