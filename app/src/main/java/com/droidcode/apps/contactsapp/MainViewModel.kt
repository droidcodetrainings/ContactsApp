package com.droidcode.apps.contactsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()

    init {
        _contacts.value = ContactsState(contacts = generateContactItems())
    }

    fun selectContact(contact: Contact) {
        _contacts.value = _contacts.value.copy(selected = contact)
        Log.i("MainActivity", "Selected contact: ${contact.firstName}")
    }

    fun unselectContact() {
        _contacts.value = _contacts.value.copy(selected = null)
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
