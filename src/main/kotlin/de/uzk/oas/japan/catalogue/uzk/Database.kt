package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Database(
    val url: String,
    val id: String,
    val link: HrefLink,
    val schema: String, // TODO enum
    @SerialName("description_short") val shortDescription: String,
    val description: String
)
