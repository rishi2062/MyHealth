package com.example.myhealth.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.myhealth.model.Person


@Dao
interface PersonDao {
    @Upsert
    suspend fun insertMyInfo(person : Person)

    @Update
    suspend fun  updateMyInfo(person : Person)

    @Delete
    suspend fun  deleteMyInfo(person : Person)


    @Query("SELECT * FROM Person")
    fun getMyInfo() : LiveData<List<Person>>

    @Query("SELECT count(id) FROM Person")
    suspend fun getDatabaseDetails() : Int


   @Query("Delete from person")
   fun deleteData()
//    suspend fun drop() : Unit

}