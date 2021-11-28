package de.uzk.oas.japan.catalogue.model.lobid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LobidResource(
    @SerialName("type") val types: List<String>,
    @SerialName("contribution") val contributions: List<Contribution> = emptyList(),
    val extent: OneOrMany<String> = OneOrMany.none(),
    val hasItem: List<HasItem>,
    @SerialName("note") val notes: List<String> = emptyList(),
    @SerialName("supplement") val supplements: List<IdentifiableResource> = emptyList(),
    @SerialName("responsibilityStatement") val responsibilityStatements: List<String> = emptyList(),
    @SerialName("alternativeTitle") val alternativeTitles: List<String> = emptyList(),
    val hasVersion: List<IdentifiableResource> = emptyList(),
    @SerialName("abstract") val abstracts: List<String> = emptyList(),
    val bibliographicCitation: String? = null,
    @SerialName("language") val languages: List<IdentifiableResource> = emptyList(),
    @SerialName("medium") val media: List<IdentifiableResource>,
    @SerialName("primaryForm") val primaryForms: List<IdentifiableResource> = emptyList(),
    val related: List<Related> = emptyList(),
    val spatial: List<Spatial> = emptyList(),
    @SerialName("subject") val subjects: List<Subject> = emptyList(),
    @SerialName("subjectAltLabel") val subjectAlternativeLabels: OneOrMany<String> = OneOrMany.none(),
    val description: List<IdentifiableResource> = emptyList(),
    val tableOfContents: List<IdentifiableResource> = emptyList(),
    val title: String,
    val titleKeyword: List<String> = emptyList(),
    val exampleOfWork: ExampleOfWork? = null,
    val containsExampleOfWork: List<ExampleOfWork> = emptyList(),
    val fulltextOnline: List<IdentifiableResource> = emptyList(),
    val hbzId: String,
    val zdbId: String? = null,
    val isPartOf: List<IsPartOf> = emptyList(),
    val inCollection: List<InCollection> = emptyList(),
    val edition: List<String> = emptyList(),
    val doi: List<String> = emptyList(),
    val urn: List<String> = emptyList(),
    val isbn: List<String> = emptyList(),
    val issn: List<String> = emptyList(),
    val ismn: List<String> = emptyList(),
    val oclcNumber: List<String> = emptyList(),
    val thesisInformation: List<String> = emptyList(),
    val predecessor: List<IdentifiableResource> = emptyList(),
    val successor: List<IdentifiableResource> = emptyList(),
    val otherTitleInformation: List<String> = emptyList(),
    val titleOfSubSeries: String? = null,
    val corporateBodyForTitle: List<String> = emptyList(),
    val natureOfContent: List<NatureOfContent> = emptyList(),
    @SerialName("shortTitle") val shortTitles: List<String> = emptyList(),
    val publication: List<Publication>,
    val similar: List<Similar> = emptyList(),
    val sameAs: List<IdentifiableResource>,
    @SerialName("label") val labels: OneOrMany<String> = OneOrMany.none(), // TODO sanity check this doesn't feel right
    @SerialName("altLabel") val alternativeLabels: OneOrMany<String> = OneOrMany.none(), // TODO sanity check this doesn't feel right
    val dateOfBirth: String? = null, // TODO sanity check this doesn't feel right
    val dateOfDeath: String? = null, // TODO sanity check this doesn't feel right
    val describedBy: DescribedBy,
    @SerialName("@context") val context: String,
    val id: String
)
