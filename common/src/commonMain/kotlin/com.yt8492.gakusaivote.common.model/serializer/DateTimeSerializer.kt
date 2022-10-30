package com.yt8492.gakusaivote.common.model.serializer

import com.soywiz.klock.DateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = DateTime::class)
object DateTimeSerializer : KSerializer<DateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: DateTime) {
        val unixMillis = value.unixMillisLong
        encoder.encodeLong(unixMillis)
    }

    override fun deserialize(decoder: Decoder): DateTime {
        val unixMillis = decoder.decodeLong()
        return DateTime(unixMillis)
    }
}