package de.uzk.oas.japan.catalogue.model.lobid

import de.uzk.oas.japan.catalogue.model.lobid.serial.ListWrappingSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class BibliographicResource : JsonLd.Typed() {
    abstract val contributions: List<Contribution>
    abstract val extent: List<String>
    abstract val hasItem: List<HasItem>
    abstract val responsibilityStatements: List<String>
    abstract val languages: List<IdentifiableResource>
    abstract val media: List<IdentifiableResource>
    abstract val subjects: List<Subject>
    abstract val subjectAlternativeLabels: List<String>
    abstract val title: String
    abstract val hbzId: String
    abstract val isPartOf: List<IsPartOf>
    abstract val oclcNumber: List<String>
    abstract val otherTitleInformation: List<String>
    abstract val publication: List<Publication>
    abstract val sameAs: List<IdentifiableResource>
    abstract val describedBy: DescribedBy
    abstract val tableOfContents: List<IdentifiableResource>
    abstract val natureOfContent: List<NatureOfContent>
    abstract val edition: List<String>
    abstract val exampleOfWork: ExampleOfWork?
    abstract val containsExampleOfWork: List<ExampleOfWork>
    abstract val alternativeTitles: List<String>
    abstract val notes: List<String>
    abstract val related: List<Related>
    abstract val corporateBodyForTitle: List<String>
    abstract val bibliographicCitation: String?
    abstract val titleKeywords: List<String>
    abstract val inCollection: List<InCollection>
    abstract val predecessors: List<IdentifiableResource>
    abstract val successors: List<IdentifiableResource>
    abstract val descriptions: List<IdentifiableResource>
    abstract val similar: List<Similar>
    abstract val abstracts: List<String>
    abstract val supplements: List<IdentifiableResource>
    abstract val shortTitles: List<String>
    abstract val hasVersion: List<IdentifiableResource>
    abstract val primaryForm: List<IdentifiableResource>
    abstract val fulltextOnline: List<IdentifiableResource>
    abstract val urn: List<String>
    abstract val doi: List<String>
}

interface PublishedScoreData {
    val ismn: List<String>
}

@Serializable
data class PublishedScore(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") override val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) override val extent: List<String> = emptyList(),
    override val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") override val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") override val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") override val media: List<IdentifiableResource>,
    @SerialName("subject") override val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) override val subjectAlternativeLabels: List<String> = emptyList(),
    override val title: String,
    override val hbzId: String,
    override val isPartOf: List<IsPartOf> = emptyList(),
    override val oclcNumber: List<String> = emptyList(),
    override val otherTitleInformation: List<String> = emptyList(),
    override val publication: List<Publication>,
    override val sameAs: List<IdentifiableResource>,
    override val describedBy: DescribedBy,
    override val tableOfContents: List<IdentifiableResource> = emptyList(),
    override val natureOfContent: List<NatureOfContent> = emptyList(),
    override val edition: List<String> = emptyList(),
    override val exampleOfWork: ExampleOfWork? = null,
    override val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") override val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") override val notes: List<String> = emptyList(),
    @SerialName("predecessor") override val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") override val successors: List<IdentifiableResource> = emptyList(),
    override val inCollection: List<InCollection> = emptyList(),
    override val related: List<Related> = emptyList(),
    override val corporateBodyForTitle: List<String> = emptyList(),
    override val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") override val titleKeywords: List<String> = emptyList(),
    @SerialName("description") override val descriptions: List<IdentifiableResource> = emptyList(),
    override val similar: List<Similar> = emptyList(),
    @SerialName("abstract") override val abstracts: List<String> = emptyList(),
    @SerialName("supplement") override val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") override val shortTitles: List<String> = emptyList(),
    override val hasVersion: List<IdentifiableResource> = emptyList(),
    override val primaryForm: List<IdentifiableResource> = emptyList(),
    override val fulltextOnline: List<IdentifiableResource> = emptyList(),
    override val urn: List<String> = emptyList(),
    override val doi: List<String> = emptyList(),
    override val ismn: List<String> = emptyList(),
    @SerialName("@context") val context: String,
) : BibliographicResource(), PublishedScoreData

@Serializable
data class Newspaper(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") override val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) override val extent: List<String> = emptyList(),
    override val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") override val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") override val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") override val media: List<IdentifiableResource>,
    @SerialName("subject") override val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) override val subjectAlternativeLabels: List<String> = emptyList(),
    override val title: String,
    override val hbzId: String,
    override val isPartOf: List<IsPartOf> = emptyList(),
    override val oclcNumber: List<String> = emptyList(),
    override val otherTitleInformation: List<String> = emptyList(),
    override val publication: List<Publication>,
    override val sameAs: List<IdentifiableResource>,
    override val describedBy: DescribedBy,
    override val tableOfContents: List<IdentifiableResource> = emptyList(),
    override val natureOfContent: List<NatureOfContent> = emptyList(),
    override val edition: List<String> = emptyList(),
    override val exampleOfWork: ExampleOfWork? = null,
    override val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") override val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") override val notes: List<String> = emptyList(),
    @SerialName("predecessor") override val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") override val successors: List<IdentifiableResource> = emptyList(),
    override val inCollection: List<InCollection> = emptyList(),
    override val related: List<Related> = emptyList(),
    override val corporateBodyForTitle: List<String> = emptyList(),
    override val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") override val titleKeywords: List<String> = emptyList(),
    @SerialName("description") override val descriptions: List<IdentifiableResource> = emptyList(),
    override val similar: List<Similar> = emptyList(),
    @SerialName("abstract") override val abstracts: List<String> = emptyList(),
    @SerialName("supplement") override val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") override val shortTitles: List<String> = emptyList(),
    override val hasVersion: List<IdentifiableResource> = emptyList(),
    override val primaryForm: List<IdentifiableResource> = emptyList(),
    override val fulltextOnline: List<IdentifiableResource> = emptyList(),
    override val urn: List<String> = emptyList(),
    override val doi: List<String> = emptyList(),
    override val issn: List<String> = emptyList(),
    override val zdbId: String? = null,
    override val titleOfSubSeries: String? = null,
    @SerialName("@context") val context: String,
) : BibliographicResource(), PeriodicalData

@Serializable
data class Image(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") override val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) override val extent: List<String> = emptyList(),
    override val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") override val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") override val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") override val media: List<IdentifiableResource>,
    @SerialName("subject") override val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) override val subjectAlternativeLabels: List<String> = emptyList(),
    override val title: String,
    override val hbzId: String,
    override val isPartOf: List<IsPartOf> = emptyList(),
    override val oclcNumber: List<String> = emptyList(),
    override val otherTitleInformation: List<String> = emptyList(),
    override val publication: List<Publication>,
    override val sameAs: List<IdentifiableResource>,
    override val describedBy: DescribedBy,
    override val tableOfContents: List<IdentifiableResource> = emptyList(),
    override val natureOfContent: List<NatureOfContent> = emptyList(),
    override val edition: List<String> = emptyList(),
    override val exampleOfWork: ExampleOfWork? = null,
    override val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") override val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") override val notes: List<String> = emptyList(),
    @SerialName("predecessor") override val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") override val successors: List<IdentifiableResource> = emptyList(),
    override val inCollection: List<InCollection> = emptyList(),
    override val related: List<Related> = emptyList(),
    override val corporateBodyForTitle: List<String> = emptyList(),
    override val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") override val titleKeywords: List<String> = emptyList(),
    @SerialName("description") override val descriptions: List<IdentifiableResource> = emptyList(),
    override val similar: List<Similar> = emptyList(),
    @SerialName("abstract") override val abstracts: List<String> = emptyList(),
    @SerialName("supplement") override val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") override val shortTitles: List<String> = emptyList(),
    override val hasVersion: List<IdentifiableResource> = emptyList(),
    override val primaryForm: List<IdentifiableResource> = emptyList(),
    override val fulltextOnline: List<IdentifiableResource> = emptyList(),
    override val urn: List<String> = emptyList(),
    override val doi: List<String> = emptyList(),
    @SerialName("@context") val context: String,
) : BibliographicResource()

@Serializable
data class Article(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") override val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) override val extent: List<String> = emptyList(),
    override val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") override val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") override val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") override val media: List<IdentifiableResource>,
    @SerialName("subject") override val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) override val subjectAlternativeLabels: List<String> = emptyList(),
    override val title: String,
    override val hbzId: String,
    override val isPartOf: List<IsPartOf> = emptyList(),
    override val oclcNumber: List<String> = emptyList(),
    override val otherTitleInformation: List<String> = emptyList(),
    override val publication: List<Publication>,
    override val sameAs: List<IdentifiableResource>,
    override val describedBy: DescribedBy,
    override val tableOfContents: List<IdentifiableResource> = emptyList(),
    override val natureOfContent: List<NatureOfContent> = emptyList(),
    override val edition: List<String> = emptyList(),
    override val exampleOfWork: ExampleOfWork? = null,
    override val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") override val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") override val notes: List<String> = emptyList(),
    @SerialName("predecessor") override val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") override val successors: List<IdentifiableResource> = emptyList(),
    override val inCollection: List<InCollection> = emptyList(),
    override val related: List<Related> = emptyList(),
    override val corporateBodyForTitle: List<String> = emptyList(),
    override val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") override val titleKeywords: List<String> = emptyList(),
    @SerialName("description") override val descriptions: List<IdentifiableResource> = emptyList(),
    override val similar: List<Similar> = emptyList(),
    @SerialName("abstract") override val abstracts: List<String> = emptyList(),
    @SerialName("supplement") override val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") override val shortTitles: List<String> = emptyList(),
    override val hasVersion: List<IdentifiableResource> = emptyList(),
    override val primaryForm: List<IdentifiableResource> = emptyList(),
    override val fulltextOnline: List<IdentifiableResource> = emptyList(),
    override val urn: List<String> = emptyList(),
    override val doi: List<String> = emptyList(),
    @SerialName("@context") val context: String,
) : BibliographicResource()

@Serializable
data class Miscellaneous(
    override val id: String? = null,
    @SerialName("type") override val types: List<String>,
    @SerialName("label") @Serializable(with = ListWrappingSerializer::class) override val labels: List<String> = emptyList(),
    @SerialName("altLabel") @Serializable(with = ListWrappingSerializer::class) override val alternativeLabels: List<String> = emptyList(),
    @SerialName("contribution") override val contributions: List<Contribution> = emptyList(),
    @Serializable(with = ListWrappingSerializer::class) override val extent: List<String> = emptyList(),
    override val hasItem: List<HasItem>,
    @SerialName("responsibilityStatement") override val responsibilityStatements: List<String> = emptyList(),
    @SerialName("language") override val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") override val media: List<IdentifiableResource>,
    @SerialName("subject") override val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") @Serializable(with = ListWrappingSerializer::class) override val subjectAlternativeLabels: List<String> = emptyList(),
    override val title: String,
    override val hbzId: String,
    override val isPartOf: List<IsPartOf> = emptyList(),
    override val oclcNumber: List<String> = emptyList(),
    override val otherTitleInformation: List<String> = emptyList(),
    override val publication: List<Publication>,
    override val sameAs: List<IdentifiableResource>,
    override val describedBy: DescribedBy,
    override val tableOfContents: List<IdentifiableResource> = emptyList(),
    override val natureOfContent: List<NatureOfContent> = emptyList(),
    override val edition: List<String> = emptyList(),
    override val exampleOfWork: ExampleOfWork? = null,
    override val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    @SerialName("alternativeTitle") override val alternativeTitles: List<String> = emptyList(),
    @SerialName("note") override val notes: List<String> = emptyList(),
    @SerialName("predecessor") override val predecessors: List<IdentifiableResource> = emptyList(),
    @SerialName("successor") override val successors: List<IdentifiableResource> = emptyList(),
    override val inCollection: List<InCollection> = emptyList(),
    override val related: List<Related> = emptyList(),
    override val corporateBodyForTitle: List<String> = emptyList(),
    override val bibliographicCitation: String? = null,
    @SerialName("titleKeyword") override val titleKeywords: List<String> = emptyList(),
    @SerialName("description") override val descriptions: List<IdentifiableResource> = emptyList(),
    override val similar: List<Similar> = emptyList(),
    @SerialName("abstract") override val abstracts: List<String> = emptyList(),
    @SerialName("supplement") override val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("shortTitle") override val shortTitles: List<String> = emptyList(),
    override val hasVersion: List<IdentifiableResource> = emptyList(),
    override val primaryForm: List<IdentifiableResource> = emptyList(),
    override val fulltextOnline: List<IdentifiableResource> = emptyList(),
    override val urn: List<String> = emptyList(),
    override val doi: List<String> = emptyList(),
    @SerialName("@context") val context: String,
    val isbn: List<String> = emptyList(),
) : BibliographicResource()
