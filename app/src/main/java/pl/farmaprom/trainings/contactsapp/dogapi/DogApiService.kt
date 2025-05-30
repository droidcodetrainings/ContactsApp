package pl.farmaprom.trainings.contactsapp.dogapi

import retrofit2.http.GET

interface DogApiService {

    @GET("breeds")
    suspend fun getBreeds(): DogApiResponse
}

