package pl.farmaprom.trainings.contactsapp.list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateFakeContactsList

class ContactsListViewModel : ViewModel() {

    var viewState by mutableStateOf(
        ContactsListViewState()
    )
        private set

    init {
        viewState = ContactsListViewState(
            list = generateFakeContactsList(count = 100)
        )
    }

    fun selectContact(contact: Contact) {
        viewState = viewState.copy(selectedContact = contact)
    }

    fun unselectContact() {
        viewState = viewState.copy(selectedContact = null)
    }
}
