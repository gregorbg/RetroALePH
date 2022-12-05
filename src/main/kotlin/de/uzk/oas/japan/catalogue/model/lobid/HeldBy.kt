package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.Serializable

@Serializable
data class HeldBy(
    override val id: String? = null,
    val label: String,
    val isil: String? = null
) : JsonLd() {
    override val labels: List<String>
        get() = listOf()
}
