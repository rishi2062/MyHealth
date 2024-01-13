package com.example.myhealth.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.myhealth.model.Schedule


@Dao
interface ScheduleDao {
    @Upsert
    suspend fun insertMySchedule(schedule : Schedule)

    @Update
    suspend fun  updateMySchedule(schedule : Schedule)

    @Delete
    suspend fun  deleteMySchedule(schedule : Schedule)


    @Query("SELECT * FROM Schedule")
    fun getMySchedule() : LiveData<List<Schedule>>

    @Query("SELECT count(id) FROM Schedule")
    suspend fun getDatabaseDetails() : Int
//    @Query("Drop ScheduleDatabase")
//    suspend fun drop() : Unit

}