package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.BibliographicTypeListFilterSerializer
import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BibliographicResource(
    override val id: String? = null,
    @SerialName("type") @Serializable(with = BibliographicTypeListFilterSerializer::class) override val types: List<BibliographicType>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) val extent: List<String> = emptyList(),
    val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") val media: List<IdentifiableResource>,
    @SerialName("subject") val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) val subjectAlternativeLabels: List<String> = emptyList(),
    val title: String,
    val hbzId: String,
    val isPartOf: List<IsPartOf> = emptyList(),
    val oclcNumber: List<String> = emptyList(),
    val otherTitleInformation: List<String> = emptyList(),
    val publication: List<Publication>,
    val sameAs: List<IdentifiableResource>,
    val describedBy: DescribedBy,
    val tableOfContents: List<IdentifiableResource> = emptyList(),
    val natureOfContent: List<NatureOfContent> = emptyList(),
    val edition: List<String> = emptyList(),
    val exampleOfWork: ExampleOfWork? = null,
    val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") val notes: List<String> = emptyList(),
    @SerialName("predecessor") val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") val successors: List<IdentifiableResource> = emptyList(),
    val inCollection: List<InCollection> = emptyList(),
    val related: List<Related> = emptyList(),
    val corporateBodyForTitle: List<String> = emptyList(),
    val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") val titleKeywords: List<String> = emptyList(),
    @SerialName("description") val descriptions: List<IdentifiableResource> = emptyList(),
    val similar: List<Similar> = emptyList(),
    @SerialName("abstract") val abstracts: List<String> = emptyList(),
    @SerialName("supplement") val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") val shortTitles: List<String> = emptyList(),
    val hasVersion: List<IdentifiableResource> = emptyList(),
    val primaryForm: List<IdentifiableResource> = emptyList(),
    val fulltextOnline: List<IdentifiableResource> = emptyList(),
    val urn: List<String> = emptyList(),
    val doi: List<String> = emptyList(),
    @SerialName("@context") val context: String,
    val isbn: List<String> = emptyList(),
    val spatial: List<Spatial> = emptyList(),
    val issn: List<String> = emptyList(),
    val zdbId: String? = null,
    val titleOfSubSeries: String? = null,
    val thesisInformation: List<String> = emptyList(),
    val dateOfBirth: String? = null, // FIXME GB wrong! report on GitHub!
    val dateOfDeath: String? = null, // FIXME GB wrong! report on GitHub!
    val ismn: List<String> = emptyList()
) : JsonLd.StrongTyped<BibliographicType>()
