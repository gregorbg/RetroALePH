package de.uzk.oas.japan.source

import de.uzk.oas.japan.catalogue.IsilSeal

interface BibHoldingSource<T> {
    fun loadBestand(institutionIsil: IsilSeal): List<T>?
}