package com.example.mycvapp.model

import com.example.mycvapp.extensions.EMPTY_STRING

data class PersonalData(
    val name: String = EMPTY_STRING,
    val photo: String = EMPTY_STRING,
    val role: String = EMPTY_STRING,
    val summary: String = EMPTY_STRING,
    val technicalSummary: String = EMPTY_STRING
)