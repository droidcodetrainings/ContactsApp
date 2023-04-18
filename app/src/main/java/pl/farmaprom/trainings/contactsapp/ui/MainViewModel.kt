package pl.farmaprom.trainings.contactsapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.farmaprom.trainings.contactsapp.contacts.presentation.ContactsViewState
import pl.farmaprom.trainings.contactsapp.sampleData

class MainViewModel : ViewModel() {

    var viewState = mutableStateOf(ContactsViewState())
        private set

    init {
        viewState.value = ContactsViewState(
            contacts = sampleData
        )
    }
}
