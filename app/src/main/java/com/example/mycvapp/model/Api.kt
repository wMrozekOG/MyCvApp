package com.example.mycvapp.model

import com.example.mycvapp.database.Person

class Api(private val requestApi: RequestApi) {

    suspend fun getPersonalData() : Person = requestApi.getPersonData()
}