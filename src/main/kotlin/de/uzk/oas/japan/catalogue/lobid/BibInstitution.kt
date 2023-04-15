package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.catalogue.lobid.serial.InstitutionTypeCamelCaseSerializer
import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BibInstitution(
    val id: String,
    val type: @Serializable(with = InstitutionTypeCamelCaseSerializer::class) InstitutionType,
    val name: String,
    @SerialName("alternateName") @Serializable(with = ListWrappingSerializer::class) val alternativeNames: List<String>,
    val linkedTo: IdentifiableResource? = null,
    @SerialName("rs") val regionalKey: String? = null,
    val telephone: String? = null,
    val mainEntityOfPage: MainEntity? = null,
    val classification: IntlLabelResource? = null,
    @SerialName("@context") val context: String,
    val url: String? = null,
    val provides: String? = null,
    val containedIn: String? = null,
    @SerialName("location") val locations: List<Location> = emptyList(),
    @SerialName("isil") val isilSeal: IsilSeal,
    @SerialName("fundertype") val funderType: IntlLabelResource? = null,
    val collects: Collects? = null,
    val email: String? = null,
    @SerialName("dbsID") val dbsId: String? = null,
    @SerialName("name_en") val nameEnglish: String? = null,
    val sameAs: List<String> = emptyList(),
)
