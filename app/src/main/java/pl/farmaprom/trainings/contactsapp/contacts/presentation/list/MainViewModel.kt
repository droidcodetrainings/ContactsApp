package pl.farmaprom.trainings.contactsapp.contacts.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContacts
import pl.farmaprom.trainings.contactsapp.dogapi.DogApiService
import retrofit2.Retrofit

class MainViewModel : ViewModel() {
    private val _contactsViewState = MutableStateFlow(ContactsViewState(emptyList()))
    val contactsViewState = _contactsViewState.asStateFlow()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dogapi.dog/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
    private val dogApiService: DogApiService = retrofit.create(DogApiService::class.java)

    init {
        Log.d("MainViewModel", "init")
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            val response = dogApiService.getBreeds()
            _contactsViewState.value = ContactsViewState(
                contacts = response.data.mapIndexed { index, singleResponseData ->
                    generateContact(index.toLong()).copy(name = singleResponseData.attributes.name)
                }
            )
        }
    }

    fun onContactSelected(contact: Contact?) {
        refresh()
        Log.d("MainViewModel", "onContactSelected: $contact")
        _contactsViewState.value = _contactsViewState.value.copy(selectedContact = contact)
    }
}
