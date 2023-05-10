package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IdProvider

interface BibItemStorage<ID : IdProvider, T> : BibItemSource<ID, T> {
    fun storeResource(bookId: ID, book: T)
}