package com.droidcode.apps.contactsapp.conatacts.data.database

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import org.joda.time.DateTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: DateTime?): Long? {
        return date?.millis
    }

    @TypeConverter
    fun fromLatLng(latLng: LatLng?): String? {
        return latLng?.let { "${it.latitude},${it.longitude}" }
    }

    @TypeConverter
    fun toLatLng(value: String?): LatLng? {
        return value?.split(",")?.let {
            if (it.size == 2) LatLng(it[0].toDouble(), it[1].toDouble()) else null
        }
    }
}
