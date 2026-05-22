package com.droidcode.apps.contactsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.conatacts.data.ContactsRepository
import com.droidcode.apps.contactsapp.conatacts.data.ContactsRepositoryImpl
import com.droidcode.apps.contactsapp.conatacts.network.ContactsApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:7070")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ContactsApiService = retrofit.create(ContactsApiService::class.java)
    private val repository: ContactsRepository = ContactsRepositoryImpl(apiService)
    private val _contacts = MutableStateFlow(ContactsState())
    val contacts: StateFlow<ContactsState> = _contacts.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            repository.getContacts().collect { contacts ->
                _contacts.update { it.copy(contacts = contacts) }
            }
        }
    }

    fun selectContact(contact: Contact) {
        _contacts.value = _contacts.value.copy(selected = contact)
        Log.i("MainActivity", "Selected contact: ${contact.firstName}")
    }

    fun unselectContact() {
        _contacts.value = _contacts.value.copy(selected = null)
    }

}
