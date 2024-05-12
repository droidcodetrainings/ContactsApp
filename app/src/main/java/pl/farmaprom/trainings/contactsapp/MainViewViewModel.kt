package pl.farmaprom.trainings.contactsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact

class MainViewViewModel(
//    private val repository: Repository
) : ViewModel() {

    var viewState by mutableStateOf(ContactsListViewState())

    init {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                repository.getData()
//            }
//        }
        val data = mutableListOf<Contact>()
        for (i in 1..1_000_000) {
            data.add(sampleData[i % 2])
        }
        viewState = viewState.copy(
            contacts = data
        )
    }

//    interface Repository {
//
//        suspend fun getData(): List<Contact>
//    }
}

data class ContactsListViewState(
    val recentContacts: List<Contact> = emptyList(),
    val contacts: List<Contact> = emptyList()
)
