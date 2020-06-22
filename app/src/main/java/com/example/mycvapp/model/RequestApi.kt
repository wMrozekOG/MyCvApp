package com.example.mycvapp.model

import com.example.mycvapp.database.Person
import retrofit2.http.GET

interface RequestApi {

    @GET("person.json")
    suspend fun getPersonData() : Person
}