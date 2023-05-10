package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IsilSeal

interface BibHoldingStorage<T> : BibHoldingSource<T> {
    fun storeBestand(institutionIsil: IsilSeal, bestand: List<T>)
}
