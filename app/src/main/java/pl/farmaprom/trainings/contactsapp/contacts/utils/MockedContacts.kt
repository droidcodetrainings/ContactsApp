package pl.farmaprom.trainings.contactsapp.contacts.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import kotlin.random.Random

fun generateContacts(numberOfContacts: Int): List<Contact> =
    mutableListOf<Contact>().apply {
        for (i in 1..numberOfContacts) {
            add(generateContact(i.toLong()))
        }
    }

fun generateContact(i: Long): Contact {
    val randomNumber = Random.nextInt(until = 4) + 1
    return Contact(
        id = i,
        name = "Name $i",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face${
            randomNumber
        }.png",
        isFavourite = randomNumber % 2L == 0L,
        phone = (i * 111111111).toString()
    )
}
