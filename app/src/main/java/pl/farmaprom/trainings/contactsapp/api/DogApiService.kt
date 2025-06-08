package pl.farmaprom.trainings.contactsapp.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header

interface DogApiService {

    @GET("breeds")
    suspend fun getBreeds(): BreedsResponse
}

