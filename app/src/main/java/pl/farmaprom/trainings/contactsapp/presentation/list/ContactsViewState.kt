package pl.farmaprom.trainings.contactsapp.presentation.list

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

data class ContactsViewState(
    val contacts: List<Contact> = emptyList(),
    val selectedContact: Contact? = null
)
