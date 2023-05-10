package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.Serializable

@Serializable
data class NextPageLink(
    val link: HrefLink
)
