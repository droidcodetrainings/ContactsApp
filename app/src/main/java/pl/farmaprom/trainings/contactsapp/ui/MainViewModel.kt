package pl.farmaprom.trainings.contactsapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.farmaprom.trainings.contactsapp.api.DogApiService
import pl.farmaprom.trainings.contactsapp.contacts.data.Contact
import pl.farmaprom.trainings.contactsapp.contacts.utils.generateContact
import pl.farmaprom.trainings.contactsapp.db.AppDatabase
import pl.farmaprom.trainings.contactsapp.presentation.list.ContactsViewState
import retrofit2.Retrofit

class MainViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    // Create OkHttpClient with logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Initialize Retrofit with Kotlin serialization
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dogapi.dog/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    // Create API service
    private val dogApiService = retrofit.create(DogApiService::class.java)

    private val database: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "app_database"
    ).build()

    private val _contactsViewState = MutableStateFlow(ContactsViewState())
    val contactsViewState = _contactsViewState.asStateFlow()

    init {
        Log.d("MainViewModel", "init")
        fetchBreeds()
    }

    fun onContactSelected(contact: Contact?) {
        Log.d("MainViewModel", "onContactSelected: $contact")
        _contactsViewState.value = _contactsViewState.value.copy(selectedContact = contact)
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                database.sampleDao().clearAll()
                val response = dogApiService.getBreeds()
                Log.d("MainViewModel", "Fetched breeds: $response")
                _contactsViewState.value = ContactsViewState(
                    contacts = response.data.mapIndexed { index, data ->
                        generateContact(
                            i = index.toLong(),
                            name = data.attributes.name
                        )
                    }
                )
            } catch (e: Exception) {
                Log.e("MainViewModel", "API call failed", e)
            }
        }
    }
}
