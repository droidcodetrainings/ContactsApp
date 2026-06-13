package com.droidcode.apps.contactsapp.conatacts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ContactsRepositoryImpl : ContactsRepository {
    override fun fetchContacts(): Flow<List<Contact>> {
        return flowOf(generateContactItems())
    }

    private fun generateContactItems(): List<Contact> {
        return List(100) { index ->
            val number = index + 1
            Contact(
                firstName = "John $number",
                lastName = "Doe",
                isFavorite = number % 5 == 0,
                email = "john.doe$number@example.com",
                phone = "123-456-789",
                city = "New York",
                relation = "Friend",
                imageUrl = "https://i.pravatar.cc/150?img=${(number % 70) + 1}"
            )
        }
    }
}
