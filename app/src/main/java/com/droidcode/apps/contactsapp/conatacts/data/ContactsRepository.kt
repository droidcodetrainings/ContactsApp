package com.droidcode.apps.contactsapp.conatacts.data

import android.util.Log
import com.droidcode.apps.contactsapp.conatacts.network.ContactsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ContactsRepository {

    //private val serviceApi = ServiceApi()
    suspend fun getContacts(): Flow<List<Contact>>
    suspend fun insertOrUpdate(contact: Contact)
}

class ContactsRepositoryImpl(private val apiService: ContactsApiService) : ContactsRepository {

    override suspend fun getContacts(): Flow<List<Contact>> = flow {
        try {
            val contacts = apiService.getContacts()
            emit(contacts)
        } catch (e: Exception) {
            // Handle error
            Log.e("ContactsRepository", "Error fetching contacts: ${e.message}")
            emit(emptyList())
        }
    }

    override suspend fun insertOrUpdate(contact: Contact) {
        TODO("Not yet implemented")
    }

}
