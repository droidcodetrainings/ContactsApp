package pl.farmaprom.trainings.contactsapp.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import kotlin.random.Random

fun generateContactsList(nums: Int):List<Contact> = mutableListOf<Contact>().apply {
    for (i in 1..nums){
        add(generateContact(i.toLong()))
    }
}

private fun generateContact(id: Long): Contact {
    val random = Random.nextInt(until = 4) + 1
    return Contact(
        id = id,
        name = "Name $id",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face${
            random
        }.png",
        isFavourite = random % 2L == 0L
    )
}
