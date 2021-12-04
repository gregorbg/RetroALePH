package de.uzk.oas.japan.catalogue.model.lobid.serial

import de.uzk.oas.japan.catalogue.model.lobid.IdentifiableResource
import de.uzk.oas.japan.catalogue.model.lobid.JsonLd
import de.uzk.oas.japan.catalogue.model.lobid.MultiVolumeBook
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.*
import kotlinx.serialization.serializerOrNull
import kotlin.reflect.KClass

class JsonLdWithTypesSerializer : JsonContentPolymorphicSerializer<JsonLd>(JsonLd::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out JsonLd> {
        val typesList = element.jsonObject["type"]?.jsonArray?.map { it.jsonPrimitive.content }.orEmpty()

        if (typesList.isEmpty()) {
            return IdentifiableResource.serializer()
        }

        if (typesList.sorted() == listOf("BibliographicResource", "MultiVolumeBook")) {
            // TODO hack! report issue to GitHub b/c data inconsistency!
            return MultiVolumeBook.serializer()
        }

        return walkSealedSubclasses(JsonLd.Typed::class, typesList)
                ?: error("No subtype ${typesList.joinToString(".")} found")
    }

    @OptIn(InternalSerializationApi::class)
    private fun <T : JsonLd.Typed> walkSealedSubclasses(
        baseType: KClass<out T>,
        ldTypes: List<String>
    ): DeserializationStrategy<out T>? {
        if (ldTypes.isEmpty()) {
            return baseType.serializerOrNull()
        }

        for (subClass in baseType.sealedSubclasses) {
            val matchingSubTypes = ldTypes.filter { it == subClass.simpleName }
            val matchingIntermediateTypes = ldTypes.filter {  "${it}Type" == subClass.simpleName  }

            // want to check perfect matches first, then our "...Types" hack if needs be.
            val matchingLdTypes = matchingSubTypes + matchingIntermediateTypes

            for (matchingType in matchingLdTypes) {
                val remainingLdTypes = ldTypes - matchingType
                val candidateSerial = walkSealedSubclasses(subClass, remainingLdTypes)

                if (candidateSerial != null) {
                    return candidateSerial
                }
            }
        }

        return null
    }
}
