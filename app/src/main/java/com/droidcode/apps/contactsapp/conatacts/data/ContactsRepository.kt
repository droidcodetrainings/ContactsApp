package com.droidcode.apps.contactsapp.conatacts.data

interface ContactsRepository {

    //private val serviceApi = ServiceApi()
    suspend fun getContacts(): List<Contact>
    suspend fun insertOrUpdate(contact: Contact)
}

class ContactsRepositoryImpl: ContactsRepository {
    override suspend fun getContacts(): List<Contact> {
        return ContactsMock().generateContactItems()
    }

    override suspend fun insertOrUpdate(contact: Contact) {
        TODO("Not yet implemented")
    }

}
