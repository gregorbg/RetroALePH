package de.uzk.oas.japan.catalogue.uzk

import de.uzk.oas.japan.catalogue.uzk.serial.KeyValueArraySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Facet(
    val language: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    val availability: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    val year: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    @SerialName("custom") val customTags: List<String>,
    val subject: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    val persons: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    @SerialName("mediatype") val mediaType: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    @SerialName("corporatebody") val corporateBody: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    @SerialName("litlist") val litList: String, // TODO proper name - figure out what this is
    val databases: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    val location: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
    val tag: String,
    val classification: List<@Serializable(with = KeyValueArraySerializer::class) KeyValuePair>,
)
