package de.uzk.oas.japan.catalogue.lobid

import de.uzk.oas.japan.catalogue.AlmaMmsId
import de.uzk.oas.japan.catalogue.HbzId
import de.uzk.oas.japan.catalogue.lobid.serial.BibliographicTypeListFilterSerializer
import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
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
    val hasItem: List<HasItem> = emptyList(), // TODO remove default value after Alma migration (only here because Aleph bibs still report no ITM in Alma test env)
    @SerialName("responsibilityStatement") val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") val media: List<IdentifiableResource>,
    @SerialName("subject") val subjects: List<Subject> = emptyList(),
    val title: String,
    val hbzId: HbzId? = null,
    val almaMmsId: AlmaMmsId,
    val deprecatedUri: String? = null, // TODO get rid after Alma migration
    val isPartOf: List<IsPartOf> = emptyList(),
    val oclcNumber: List<String> = emptyList(),
    val otherTitleInformation: List<String> = emptyList(),
    val publication: List<Publication>,
    val sameAs: List<IdentifiableResource> = emptyList(),
    val containedIn: List<IdentifiableResource> = emptyList(),
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
    @SerialName("abstract") val abstracts: List<String> = emptyList(),
    @SerialName("supplement") val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") val shortTitles: List<String> = emptyList(),
    val primaryForm: List<IdentifiableResource> = emptyList(),
    val fulltextOnline: List<IdentifiableResource> = emptyList(),
    val urn: List<String> = emptyList(),
    val doi: List<String> = emptyList(),
    @SerialName("@context") val context: String,
    val isbn: List<String> = emptyList(),
    val spatial: List<Spatial> = emptyList(),
    val issn: List<String> = emptyList(),
    val zdbId: String? = null,
    val thesisInformation: List<String> = emptyList(),
    val dateOfBirth: String? = null, // FIXME GB wrong! report on GitHub!
    val dateOfDeath: String? = null, // FIXME GB wrong! report on GitHub!
    val ismn: List<String> = emptyList(),
    val webPageArchived: List<IdentifiableResource> = emptyList(),
) : JsonLd.StrongTyped<BibliographicType>()
