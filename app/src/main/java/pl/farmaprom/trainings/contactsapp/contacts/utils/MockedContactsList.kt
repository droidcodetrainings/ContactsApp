package pl.farmaprom.trainings.contactsapp.contacts.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import kotlin.random.Random

fun generateContactsList(nums: Long): List<Contact> = mutableListOf<Contact>().apply {
    for(i in 1..nums){
        add(generateContact(i))
    }
}

fun generateContact(i: Long, name: String = "Name $i"): Contact {
    val random = Random.nextInt(until = 4) + 1
    return Contact(
        id = i,
        name = name,
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face${
            random
        }.png",
        isFavourite = random % 2L == 0L
    )
}
