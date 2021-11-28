package de.uzk.oas.japan.catalogue.model

import de.uzk.oas.japan.library.BookSignature
import de.uzk.oas.japan.library.LibrarySection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StammdateiEntry(
    @SerialName("Art") val art: String,
    @SerialName("Ausgabe") val ausgabe: String,
    @SerialName("Autor") val autor: String,
    @SerialName("AutorJap") val autorJpn: String,
    @SerialName("AV") val av: String,
    @SerialName("Datensatzanzahl") val datensatzAnzahl: String,
    @SerialName("Datum") val datum: String,
    @SerialName("Doubletten_intern") val doublettenIntern: String,
    @SerialName("Enthalten") val enthalten: String,
    @SerialName("EnthaltenJap") val enthaltenJpn: String,
    @SerialName("Fußnote") val fussnote: String,
    @SerialName("GesamtJap") val gesamtJpn: String,
    @SerialName("Gesamttitel") val gesamtTitel: String,
    @SerialName("Inventar") val inventar: String,
    @SerialName("ISBN") val isbn: String,
    @SerialName("Jahr") val jahr: String,
    @SerialName("Jg,Heft,Bd") val jgHeftBand: String,
    @SerialName("Kollation") val kollation: String,
    @SerialName("Nummer") val nummer: String,
    @SerialName("Ort") val ort: String,
    @SerialName("OrtJap") val ortJpn: String,
    @SerialName("Recherche") val recherche: String,
    @SerialName("Reihe") val reihe: String,
    @SerialName("ReiheJap") val reiheJpn: String,
    @SerialName("Schlagwort") val schlagwort: String,
    @SerialName("Signatur") val signatur: String,
    @SerialName("Signatur_flach") val signaturFlach: String,
    @SerialName("Sprache") val sprache: String,
    @SerialName("Standort erweitert") val standortErweitert: String,
    @SerialName("Titel") val titel: String,
    @SerialName("TitelJap") val titelJpn: String,
    @SerialName("Ver") val verleger: String,
    @SerialName("VerJap") val verlegerJpn: String,
    @SerialName("Verlag") val verlag: String,
    @SerialName("VerlJap") val verlagJpn: String
) {
    fun parseSignature(): BookSignature? {
        if (this.signaturFlach.isBlank())
            return null

        if (this.signaturFlach.startsWith(LibrarySection.JADE.signatureTag, ignoreCase = true)) {
            val indexSpec = this.signaturFlach.split("\\s".toRegex()).last()
            val (index, subIndex) = parseIndexSpec(indexSpec)

            return BookSignature.JaDe(index, subIndex)
        }

        val (signatureTag, categorySpec, authorTag, indexSpec) = this.signaturFlach.trim().split("\\s+".toRegex())

        val section = LibrarySection.fromSignatureTag(signatureTag)

        val (category, subCategory) = parseIndexSpec(categorySpec)
        val (index, subIndex) = parseIndexSpec(indexSpec)

        return BookSignature.Standard(section, category, subCategory, authorTag, index, subIndex)
    }

    companion object {
        private fun parseIndexSpec(indexSpec: String): Pair<Int, Int?> {
            // Hack for aggregated MtM entries
            if ("-" in indexSpec) {
                val (firstInRow, _) = indexSpec.split("-")
                return parseIndexSpec(firstInRow)
            }

            if ("." in indexSpec) {
                val (index, subIndex) = indexSpec.split(".").map { it.toInt() }
                return index to subIndex
            }

            return indexSpec.toInt() to null
        }
    }
}
