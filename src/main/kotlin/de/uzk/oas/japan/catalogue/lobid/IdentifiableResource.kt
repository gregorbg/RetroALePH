package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
data class IdentifiableResource(
    val id: String,
    //@SerialName("label") @Serializable(with = ListWrappingSerializer::class) val labels: List<String> = emptyList(),
    val label: String,
)
