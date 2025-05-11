package pl.farmaprom.trainings.contactsapp.utils

import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import kotlin.random.Random

fun generateContactsList(nums: Long): List<Contact> = mutableListOf<Contact>().apply {
    for(i in 1..nums){
        add(generateContact(i))
    }
}

private fun generateContact(i: Long): Contact {
    val random = Random.nextInt(until = 4) + 1
    return Contact(
        id = i,
        name = "Name $i",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face${
            random
        }.png",
        isFavourite = random % 2L == 0L
    )
}
