package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMetadata(
    @SerialName("next_page") val nextPage: NextPageLink,
    val hits: Int,
    val page: String,
    @SerialName("end_range") val endRange: Int,
    val num: String,
    @SerialName("start_range") val startRange: Int,
    @SerialName("ips_user") val ipsUser: String,
)
