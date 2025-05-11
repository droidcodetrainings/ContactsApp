package pl.farmaprom.trainings.contactsapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.farmaprom.trainings.contactsapp.contacts.presentation.list.ContactsViewState
import pl.farmaprom.trainings.contactsapp.utils.generateContactsList

class MainViewModel : ViewModel() {

    private val _contactsViewState = MutableStateFlow(ContactsViewState())
    val contactViewState = _contactsViewState.asStateFlow()

    init {
        Log.d("MainViewModel", "init")
        _contactsViewState.value = ContactsViewState(contacts = generateContactsList(100))
    }
}
