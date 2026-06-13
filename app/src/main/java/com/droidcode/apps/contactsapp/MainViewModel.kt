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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()
    
    private val database = AppDatabase.instance
    private val repository = ContactsRepository(database.contactDao())
    
    init {
        viewModelScope.launch {
            // Replace generating contacts in MainViewModel by adding generated contacts into roomdatabase on initialization
            repository.insertContacts(generateContactItems())
            
            // Get contacts as source of contacts fetched from database dao method
            repository.getContacts().collectLatest { contactsList ->
                _contacts.value = _contacts.value.copy(contacts = contactsList)
            }
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
