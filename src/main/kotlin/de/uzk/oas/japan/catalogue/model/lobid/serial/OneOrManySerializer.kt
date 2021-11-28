package de.uzk.oas.japan.catalogue.model.lobid.serial

import de.uzk.oas.japan.catalogue.model.lobid.OneOrMany
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = OneOrMany::class)
class OneOrManySerializer<T : Any>(
    private val singleValueSerializer: KSerializer<T>
) : KSerializer<OneOrMany<T>> {
    override val descriptor: SerialDescriptor
        get() = singleValueSerializer.descriptor

    private val manyValuesSerializer = ListSerializer(singleValueSerializer)

    override fun deserialize(decoder: Decoder): OneOrMany<T> {
        return try {
            val maybeList = manyValuesSerializer.deserialize(decoder)
            OneOrMany(maybeList)
        } catch (ex: SerializationException) {
            val singleValue = singleValueSerializer.nullable.deserialize(decoder)
                ?: return OneOrMany.none()

            OneOrMany(listOf(singleValue))
        }
    }

    override fun serialize(encoder: Encoder, value: OneOrMany<T>) {
        when (value.size) {
            0 -> encoder.encodeNull()
            1 -> encoder.encodeSerializableValue(singleValueSerializer, value.single())
            else -> encoder.encodeSerializableValue(manyValuesSerializer, value)
        }

    }
}