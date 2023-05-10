package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IsilSeal
import de.uzk.oas.japan.catalogue.UzkCatalogueId
import de.uzk.oas.japan.catalogue.uzk.CatalogueTitle
import de.uzk.oas.japan.catalogue.uzk.SearchResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking

class UzkCatalogueApi(
    val catalogueId: String,
    val holdingStorage: BibHoldingStorage<CatalogueTitle>,
    val itemStorage: BibItemStorage<UzkCatalogueId, CatalogueTitle>,
    val resultsPerPage: Int = DEFAULT_RESULTS_PER_PAGE
) : BibHoldingSource<CatalogueTitle>, BibItemSource<UzkCatalogueId, CatalogueTitle> {
    override fun loadBestand(institutionIsil: IsilSeal): List<CatalogueTitle> {
        return holdingStorage.loadBestand(institutionIsil) ?: runBlocking {
            KTOR_CLIENT.use { ktor ->
                val searchResult = ktor.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BASE_HOST

                        appendPathSegments("portal", "search.json")

                        parameter("num", resultsPerPage)
                        parameter("page", 1) // TODO paginate!
                        parameter("tab", "books") // TODO add support for other UB website tabs?
                        parameter("f[floc]", institutionIsil.id)
                    }
                }.body<SearchResult>()

                // TODO paginate
                searchResult.records
                    .also { holdingStorage.storeBestand(institutionIsil, it) }
            }
        }
    }

    override fun loadResource(id: UzkCatalogueId): CatalogueTitle? {
        return itemStorage.loadResource(id) ?: try {
            runBlocking {
                KTOR_CLIENT.use {
                    it.get {
                        url {
                            protocol = URLProtocol.HTTPS
                            host = BASE_HOST

                            appendPathSegments("portal", "databases", "id", catalogueId, "titles", "id", "${id.id}.json")
                        }
                    }.body<CatalogueTitle>()
                }.also { itemStorage.storeResource(id, it) }
            }
        } catch (e: ClientRequestException) {
            return null
        }
    }

    companion object {
        const val BASE_HOST = "katalog.ub.uni-koeln.de"

        const val DEFAULT_RESULTS_PER_PAGE = 20

        private val KTOR_CLIENT
            get() = HttpClient(Java) {
                expectSuccess = true

                install(ContentNegotiation) {
                    json()
                }
            }
    }
}