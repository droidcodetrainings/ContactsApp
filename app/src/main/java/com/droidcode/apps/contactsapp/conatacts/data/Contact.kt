package com.droidcode.apps.contactsapp.conatacts.data

import com.google.android.gms.maps.model.LatLng
import org.joda.time.DateTime

data class Contact(
    val id: Long = -1,
    val firstName: String,
    val lastName: String,
    val relation: String? = null,
    val email: String = "",
    val phone: String = "",
    val city: String? = null,
    val birthday: DateTime? = null,
    val location: LatLng? = null,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    val isMe: Boolean = false,
    val lastActionTime: Long? = null
)
