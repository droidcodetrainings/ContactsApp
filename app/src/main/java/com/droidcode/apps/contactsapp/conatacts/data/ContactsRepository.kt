package com.droidcode.apps.contactsapp.conatacts.data

import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun fetchContacts(): Flow<List<Contact>>
}
