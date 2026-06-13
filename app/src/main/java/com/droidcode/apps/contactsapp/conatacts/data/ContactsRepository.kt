package com.droidcode.apps.contactsapp.conatacts.data

import com.droidcode.apps.contactsapp.conatacts.data.database.ContactDao
import com.droidcode.apps.contactsapp.conatacts.data.database.ContactDto
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.joda.time.DateTime

class ContactsRepository(private val contactDao: ContactDao) {

    fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAll().map { dtos ->
            dtos.map { it.toDomain() }
        }
    }

    suspend fun insertContacts(contacts: List<Contact>) {
        contactDao.insertAll(contacts.map { it.toDto() })
    }

    private fun ContactDto.toDomain(): Contact = Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        relation = relation,
        email = email,
        phone = phone,
        city = city,
        birthday = birthday?.let { DateTime(it) },
        location = if (latitude != null && longitude != null) LatLng(latitude, longitude) else null,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        isMe = isMe,
        lastActionTime = lastActionTime
    )

    private fun Contact.toDto(): ContactDto = ContactDto(
        id = if (id == -1L) 0 else id, // Room uses 0 for auto-generate
        firstName = firstName,
        lastName = lastName,
        relation = relation,
        email = email,
        phone = phone,
        city = city,
        birthday = birthday?.millis,
        latitude = location?.latitude,
        longitude = location?.longitude,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        isMe = isMe,
        lastActionTime = lastActionTime
    )
}
