package pl.farmaprom.trainings.contactsapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.list.presentation.ContactsViewState
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.dogapi.DogApiService
import retrofit2.Retrofit

class MainViewModel : ViewModel() {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dogapi.dog/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
    private val dogApiService: DogApiService = retrofit.create(DogApiService::class.java)

    private val _contactsViewState: MutableStateFlow<ContactsViewState> =
        MutableStateFlow(ContactsViewState(contacts = emptyList()))
    val contactsViewState: StateFlow<ContactsViewState> = _contactsViewState.asStateFlow()

    init {
        Log.d("MainViewModel", "init")
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            val response = dogApiService.getBreeds()
            response.data.forEach {
                Log.d("MainViewModel", "breed: ${it.attributes.name}")
            }
            _contactsViewState.value = ContactsViewState(
                contacts = response.data.mapIndexed { index, breed ->
                    generateContact(index.toLong()).copy(name = breed.attributes.name)
                }
            )
        }
    }

    fun onContactSelected(contact: Contact?) {
        Log.d("MainViewModel", "onContactSelected: $contact")
        _contactsViewState.value = _contactsViewState.value.copy(selectedContact = contact)
    }
}

