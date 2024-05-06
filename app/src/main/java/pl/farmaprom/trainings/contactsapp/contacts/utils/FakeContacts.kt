package pl.farmaprom.trainings.contactsapp.contacts.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

fun generateFakeContactsList(count: Int) = mutableListOf<Contact>().apply {
    for (i in 1..count) {
        add(generateFakeContact(id = i.toLong()))
    }
}

fun generateFakeContact(id: Long) = Contact(
    id = id,
    name = "name $id",
    additionalInfo = "additional",
    email = "abcd@fgh.pl",
    imageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/%20%20kamper.jpg"
)
