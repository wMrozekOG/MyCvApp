package com.example.mycvapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun get() : LiveData<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(person: Person)
}