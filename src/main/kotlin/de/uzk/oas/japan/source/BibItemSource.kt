package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IdProvider

interface BibItemSource<ID : IdProvider, T> {
    fun loadResource(id: ID): T?
}