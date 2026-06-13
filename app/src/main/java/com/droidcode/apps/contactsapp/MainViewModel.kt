package com.droidcode.apps.contactsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.conatacts.data.ContactsRepository
import com.droidcode.apps.contactsapp.conatacts.data.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()

    private val repository = ContactsRepository(AppDatabase.instance.getContactDao())

    init {
        observeContacts()
    }

    private fun observeContacts() {
        repository.getAllContacts()
            .onEach { contactList ->
                _contacts.value = _contacts.value.copy(contacts = contactList)
                if (contactList.isEmpty()) {
                    insertInitialContacts()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun insertInitialContacts() {
        viewModelScope.launch {
            repository.insertContacts(generateContactItems())
        }
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
