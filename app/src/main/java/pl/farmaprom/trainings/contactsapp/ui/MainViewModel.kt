package pl.farmaprom.trainings.contactsapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsViewState
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts

class MainViewModel : ViewModel() {

    private val _contactsViewState: MutableStateFlow<ContactsViewState> =
        MutableStateFlow(ContactsViewState(contacts = emptyList()))
    val contactsViewState: StateFlow<ContactsViewState> = _contactsViewState.asStateFlow()

    init {
        Log.d("MainViewModel", "init")
        _contactsViewState.value = ContactsViewState(contacts = generateContacts(100))
    }

    fun onContactSelected(contact: Contact) {
        Log.d("MainViewModel", "onContactSelected: $contact")
        _contactsViewState.value = _contactsViewState.value.copy(selectedContact = contact)
    }
}