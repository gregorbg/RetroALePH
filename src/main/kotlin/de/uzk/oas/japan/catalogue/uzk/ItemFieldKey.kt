package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ItemFieldKey {
    @SerialName("X0005") INVENTORY_NUMBER,
    @SerialName("X0010") SERIAL_NUMBER,
    @SerialName("X0014") CALL_NUMBER,
    @SerialName("X0016") SOURCE_CATALOGUE,
    @SerialName("X4000") SOURCE_INSTITUTION, // unsure
    @SerialName("X0004") UNKNOWN_X0004,
    @SerialName("X0107") UNKNOWN_X0107,
    @SerialName("X1204") UNKNOWN_X1204,
    @SerialName("X3330") UNKNOWN_X3330,
    @SerialName("X4001") UNKNOWN_X4001,
}
