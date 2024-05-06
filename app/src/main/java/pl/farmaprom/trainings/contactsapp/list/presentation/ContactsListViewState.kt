package pl.farmaprom.trainings.contactsapp.list.presentation

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

data class ContactsListViewState(
    val list: List<Contact> = emptyList(),
    val recent: List<Contact> = emptyList(),
    val selectedContact: Contact? = null
)
