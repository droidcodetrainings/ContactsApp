package pl.farmaprom.trainings.contactsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

class MainViewModel : ViewModel() {

    var viewState by mutableStateOf(ContactsListViewState())

    init {
        val data = mutableListOf<Contact>()

        for (i in 1..1_000_000) {
            data.add(sampleData[i % 2])
        }

        viewState = viewState.copy(
            contactsList = data
        )
    }
}

data class ContactsListViewState(
    val recentContacts: List<Contact> = emptyList(),
    val contactsList: List<Contact> = emptyList()
)
