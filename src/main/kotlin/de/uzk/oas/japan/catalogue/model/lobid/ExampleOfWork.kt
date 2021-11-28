package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleOfWork(
    val id: String? = null,
    @SerialName("type") val types: List<String>,
    val creatorOfWork: String? = null,
    @SerialName("label") val labels: OneOrMany<String>
)
