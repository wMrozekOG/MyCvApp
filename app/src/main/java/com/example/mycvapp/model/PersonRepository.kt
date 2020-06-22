package com.example.mycvapp.model

import androidx.lifecycle.LiveData
import com.example.mycvapp.database.Person

class PersonRepository constructor(
    private val personResource: PersonResource
) {

    val source: LiveData<Person> = personResource.source

    val requestState = personResource.requestState

    suspend fun refresh() {
        personResource.get()
    }
}