package pl.farmaprom.trainings.contactsapp.contacts.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import kotlin.random.Random

fun generateContacts(numberOfContacts: Int = 10): List<Contact> {
    return mutableListOf<Contact>().apply {
        for (i in 1..numberOfContacts) {
            this.add(generateContact(i.toLong()))
        }
    }
}

fun generateContact(id: Long): Contact {
    val randomNumber = Random.nextInt(until = 4) + 1
    return Contact(
        id = id,
        name = "Name $id",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face${randomNumber}.png",
        isFavourite = randomNumber % 2 == 0
    )
}
