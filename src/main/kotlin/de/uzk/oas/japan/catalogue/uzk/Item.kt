package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val fields: Map<ItemFieldKey, ItemField>
)
