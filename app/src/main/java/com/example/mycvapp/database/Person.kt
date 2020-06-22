package com.example.mycvapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycvapp.extensions.EMPTY_STRING
import com.google.gson.annotations.SerializedName

@Entity(tableName = "person")
data class Person(
    @PrimaryKey @SerializedName("name") @ColumnInfo(defaultValue = EMPTY_STRING) val name: String,
    @SerializedName("photo") @ColumnInfo(defaultValue = EMPTY_STRING) val photo: String,
    @SerializedName("role") @ColumnInfo(defaultValue = EMPTY_STRING) val role: String,
    @SerializedName("summary") @ColumnInfo(defaultValue = EMPTY_STRING) val summary: String,
    @SerializedName("work_history") val workHistory: List<WorkHistoryEntry>,
    @SerializedName("technical_summary") @ColumnInfo(defaultValue = EMPTY_STRING) val technicalSummary: String
)

data class WorkHistoryEntry(
    @SerializedName("title") @ColumnInfo(defaultValue = EMPTY_STRING) val title: String,
    @SerializedName("company") @ColumnInfo(defaultValue = EMPTY_STRING) val company: String,
    @SerializedName("company_logo") @ColumnInfo(defaultValue = EMPTY_STRING) val companyLogo: String,
    @SerializedName("start_date") @ColumnInfo(defaultValue = "0")val startDate: Long,
    @SerializedName("duration") @ColumnInfo(defaultValue = EMPTY_STRING) val duration: String,
    @SerializedName("description") @ColumnInfo(defaultValue = EMPTY_STRING) val description: String
)