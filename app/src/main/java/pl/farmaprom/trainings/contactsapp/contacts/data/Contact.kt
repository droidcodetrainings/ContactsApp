package pl.farmaprom.trainings.contactsapp.contacts.data

import org.joda.time.DateTime

data class Contact(
    val id: Long,
    val name: String,
    val additionalInfo: String = "",
    val email: String = "",
    val imageUrl: String? = null,
    val isFavourite: Boolean = false,
    val lastInteractionTime: DateTime? = null
)
