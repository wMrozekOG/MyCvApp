package com.example.mycvapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListConverters {

    @TypeConverter
    fun fromWorkHistoryList(workHistory: List<WorkHistoryEntry?>?): String? {
        if (workHistory == null) {
            return null
        }
        val type: Type = object : TypeToken<List<WorkHistoryEntry?>?>() {}.type
        return Gson().toJson(workHistory, type)
    }

    @TypeConverter
    fun toWorkHistoryList(workHistoryString: String?): List<WorkHistoryEntry>? {
        if (workHistoryString == null) {
            return null
        }
        val type: Type = object : TypeToken<List<WorkHistoryEntry?>?>() {}.type
        return Gson().fromJson(workHistoryString, type)
    }
}