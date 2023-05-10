package de.uzk.oas.japan.catalogue.uzk

import de.uzk.oas.japan.catalogue.UzkCatalogueId
import de.uzk.oas.japan.catalogue.lobid.serial.ListWrappingSerializer
import de.uzk.oas.japan.catalogue.uzk.serial.ItemSerializer
import de.uzk.oas.japan.catalogue.uzk.serial.TitleFieldHackSerializer
import kotlinx.serialization.Serializable

@Serializable
data class CatalogueTitle(
    val fields: Map<TitleFieldKey, @Serializable(with = ListWrappingSerializer::class) List<@Serializable(with = TitleFieldHackSerializer::class) TitleField>>,
    val database: Database,
    val flags: Flags,
    val items: List<@Serializable(with = ItemSerializer::class) Item> = emptyList(), // missing in global search
    val locations: List<Location>,
    val circulation: List<Holding> = emptyList(), // missing in global search
    val link: HrefLink,
    val id: UzkCatalogueId,
)
