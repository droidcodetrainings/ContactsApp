package com.droidcode.apps.contactsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.conatacts.data.ContactsRepository
import com.droidcode.apps.contactsapp.conatacts.data.ContactsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository: ContactsRepository = ContactsRepositoryImpl()

    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchContacts().collect { contactList ->
                _contacts.value = _contacts.value.copy(contacts = contactList)
            }
        }
    }

    fun selectContact(contact: Contact) {
        _contacts.value = _contacts.value.copy(selected = contact)
        Log.i("MainActivity", "Selected contact: ${contact.firstName}")
    }
}
