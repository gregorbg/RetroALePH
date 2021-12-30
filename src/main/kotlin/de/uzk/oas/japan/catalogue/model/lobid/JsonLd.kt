package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.Serializable

@Serializable
sealed class JsonLd {
    abstract val id: String?
    abstract val labels: List<String>

    @Serializable
    sealed class Typed<T> : JsonLd() {
        abstract val types: List<T>
        abstract val alternativeLabels: List<String>
    }

    @Serializable
    sealed class WeakTyped : Typed<String>()

    @Serializable
    sealed class StrongTyped<T : Enum<T>> : Typed<T>()
}
