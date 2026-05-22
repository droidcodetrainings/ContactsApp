package com.droidcode.apps.contactsapp.conatacts.network

import com.droidcode.apps.contactsapp.conatacts.data.Contact
import retrofit2.http.GET

interface ContactsApiService {

    @GET("/contacts")
    suspend fun getContacts(): List<Contact>
}
