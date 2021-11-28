package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.OneOrManySerializer
import kotlinx.serialization.Serializable

@Serializable(with = OneOrManySerializer::class)
data class OneOrMany<T>(val values: List<T>) : List<T> by values {
    companion object {
        fun <T> none(): OneOrMany<T> = OneOrMany(emptyList())
    }
}
