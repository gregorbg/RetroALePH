package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.JsonLdWithTypesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = JsonLdWithTypesSerializer::class)
sealed class JsonLd {
    abstract val id: String?
    abstract val labels: List<String>

    @Serializable
    sealed class Typed : JsonLd() {
        abstract val types: List<String>
        abstract val alternativeLabels: List<String>
    }
}
