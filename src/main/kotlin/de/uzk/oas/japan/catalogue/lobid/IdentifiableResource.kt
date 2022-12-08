package de.uzk.oas.japan.catalogue.lobid

import kotlinx.serialization.Serializable

@Serializable
data class IdentifiableResource(
    override val id: String? = null,
    val label: String
) : JsonLd() {
    override val labels: List<String>
        get() = listOf(label)
}
