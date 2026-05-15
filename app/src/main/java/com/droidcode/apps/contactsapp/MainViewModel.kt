package com.droidcode.apps.contactsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.conatacts.data.ContactsMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()

    init {
        _contacts.value = ContactsState(contacts = ContactsMock().generateContactItems())
    }

    fun selectContact(contact: Contact) {
        _contacts.value = _contacts.value.copy(selected = contact)
        Log.i("MainActivity", "Selected contact: ${contact.firstName}")
    }

    fun unselectContact() {
        _contacts.value = _contacts.value.copy(selected = null)
    }

}
