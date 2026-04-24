package pl.farmaprom.trainings.contactsapp

import com.droidcode.apps.contactsapp.conatacts.data.Contact


//val CONTENT_TYPE = "application/json".toMediaType()
const val API_URL = "http://trainingcontactsapp-env.eba-x3t56ejz.eu-central-1.elasticbeanstalk.com/"
const val API_KEY = ""

val sampleData = listOf(
    Contact(
        id = 1L,
        firstName = "Golden",
        lastName = "Kamper",
        email = "kamper@dog.com",
        phone = "123456",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/%20%20kamper.jpg",
        isFavourite = true
    ),
    Contact(
        id = 2L,
        firstName = "Tuchel",
        lastName = "Wuefista",
        email = "tuchel@czelsi.com",
        phone = "553523532",
        profileImageUrl = "https://raw.githubusercontent.com/kamilruchalaf/trainingassets/main/assets/face1.png",
        isFavourite = false
    )
)
