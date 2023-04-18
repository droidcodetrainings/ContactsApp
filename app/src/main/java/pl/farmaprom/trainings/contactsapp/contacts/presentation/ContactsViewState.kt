package pl.farmaprom.trainings.contactsapp.contacts.presentation

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

data class ContactsViewState(
    val profile: Contact? = null,
    val recentContact: List<Contact> = emptyList(),
    val contacts: List<Contact> = emptyList()
)
