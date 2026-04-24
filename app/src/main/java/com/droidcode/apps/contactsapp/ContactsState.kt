package com.droidcode.apps.contactsapp

import com.droidcode.apps.contactsapp.conatacts.data.Contact

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val selected: Contact? = null
)

