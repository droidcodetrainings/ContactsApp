package com.droidcode.apps.contactsapp.conatacts.data

import com.droidcode.apps.contactsapp.conatacts.data.database.ContactDao
import com.droidcode.apps.contactsapp.conatacts.data.database.ContactDto
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.joda.time.DateTime

class ContactsRepository(private val contactDao: ContactDao) {

    fun getContacts(): Flow<List<Contact>> {
        return contactDao.getAll().map { dtos ->
            dtos.map { dto ->
                Contact(
                    id = dto.id,
                    firstName = dto.firstName,
                    lastName = dto.lastName,
                    relation = dto.relation,
                    email = dto.email,
                    phone = dto.phone,
                    city = dto.city,
                    birthday = dto.birthday?.let { DateTime(it) },
                    location = if (dto.latitude != null && dto.longitude != null) {
                        LatLng(dto.latitude, dto.longitude)
                    } else null,
                    imageUrl = dto.imageUrl,
                    isFavorite = dto.isFavorite,
                    isMe = dto.isMe,
                    lastActionTime = dto.lastActionTime
                )
            }
        }
    }

    suspend fun insertContacts(contacts: List<Contact>) {
        contactDao.insertAll(contacts.map { contact ->
            ContactDto(
                id = if (contact.id == -1L) 0 else contact.id,
                firstName = contact.firstName,
                lastName = contact.lastName,
                relation = contact.relation,
                email = contact.email,
                phone = contact.phone,
                city = contact.city,
                birthday = contact.birthday?.millis,
                latitude = contact.location?.latitude,
                longitude = contact.location?.longitude,
                imageUrl = contact.imageUrl,
                isFavorite = contact.isFavorite,
                isMe = contact.isMe,
                lastActionTime = contact.lastActionTime
            )
        })
    }
}
