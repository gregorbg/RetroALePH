package de.uzk.oas.japan.rdaconvert.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StammdateiEntry(
    @SerialName("Art") val art: String? = null,
    @SerialName("Ausgabe") val ausgabe: String? = null,
    @SerialName("Autor") val autor: String? = null,
    @SerialName("AutorJap") val autorJpn: String? = null,
    @SerialName("AV") val av: String? = null,
    @SerialName("Datensatzanzahl") val datensatzAnzahl: String? = null,
    @SerialName("Datum") val datum: String? = null,
    @SerialName("Doubletten_intern") val doublettenIntern: String? = null,
    @SerialName("Enthalten") val enthalten: String? = null,
    @SerialName("EnthaltenJap") val enthaltenJap: String? = null,
    @SerialName("Fußnote") val fussnote: String? = null,
    @SerialName("GesamtJap") val gesamtJpn: String? = null,
    @SerialName("Gesamttitel") val gesamtTitel: String? = null,
    @SerialName("Inventar") val inventar: String? = null,
    @SerialName("ISBN") val isbn: String? = null,
    @SerialName("Jahr") val jahr: String,
    @SerialName("Jg,Heft,Bd") val jgHeftBand: String? = null,
    @SerialName("Kollation") val kollation: String? = null,
    @SerialName("Nummer") val nummer: String? = null,
    @SerialName("Ort") val ort: String? = null,
    @SerialName("OrtJap") val ortJap: String? = null,
    @SerialName("Recherche") val recherche: String? = null,
    @SerialName("Reihe") val reihe: String? = null,
    @SerialName("ReiheJap") val reiheJap: String? = null,
    @SerialName("Schlagwort") val schlagwort: String? = null,
    @SerialName("Signatur") val signatur: String? = null,
    @SerialName("Signatur_flach") val signaturFlach: String? = null,
    @SerialName("Sprache") val sprache: String? = null,
    @SerialName("Standort erweitert") val standortErweitert: String? = null,
    @SerialName("Titel") val titel: String? = null,
    @SerialName("TitelJap") val titelJpn: String? = null,
    @SerialName("Ver") val verleger: String? = null,
    @SerialName("VerJap") val verlegerJpn: String? = null,
    @SerialName("Verlag") val verlag: String? = null,
    @SerialName("VerlJap") val verlagJpn: String? = null
)
