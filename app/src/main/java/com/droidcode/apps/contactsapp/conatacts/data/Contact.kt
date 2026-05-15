package com.droidcode.apps.contactsapp.conatacts.data

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.joda.time.DateTime

@Serializable
data class Contact(
    val id: Long = -1,
    val firstName: String,
    val lastName: String,
    val relation: String? = null,
    val email: String = "",
    val phone: String = "",
    val city: String? = null,
    @Serializable(with = DateTimeSerializer::class)
    val birthday: DateTime? = null,
    @Serializable(with = LatLngSerializer::class)
    val location: LatLng? = null,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    val isMe: Boolean = false,
    val lastActionTime: Long? = null
)

object DateTimeSerializer : KSerializer<DateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: DateTime) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): DateTime = DateTime.parse(decoder.decodeString())
}

object LatLngSerializer : KSerializer<LatLng> {
    @Serializable
    private data class LatLngSurrogate(val latitude: Double, val longitude: Double)

    override val descriptor: SerialDescriptor = LatLngSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: LatLng) {
        val surrogate = LatLngSurrogate(value.latitude, value.longitude)
        encoder.encodeSerializableValue(LatLngSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): LatLng {
        val surrogate = decoder.decodeSerializableValue(LatLngSurrogate.serializer())
        return LatLng(surrogate.latitude, surrogate.longitude)
    }
}
