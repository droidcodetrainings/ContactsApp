package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

data class ContactsViewState(
    val contacts: List<Contact>,
    val selectedContact: Contact? = null
)
