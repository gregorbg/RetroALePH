package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleOfWork(
    val id: String? = null,
    @SerialName("type") val types: List<String> = emptyList(),
    val label: String? = null,
    val creatorOfWork: String? = null,
    val workNumbering: String? = null,
    val instrumentation: List<String> = emptyList(),
)
