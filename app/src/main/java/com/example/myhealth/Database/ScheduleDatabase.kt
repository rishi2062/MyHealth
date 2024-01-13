package com.example.myhealth.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myhealth.Dao.PersonDao
import com.example.myhealth.Dao.ScheduleDao
import com.example.myhealth.model.Person
import com.example.myhealth.model.Schedule

@Database(entities = [Schedule::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao() : ScheduleDao

    companion object{
        @Volatile
        private var INSTANCE : ScheduleDatabase? = null

        fun getDatabase(context: Context): ScheduleDatabase {
            return INSTANCE ?: synchronized(true){
                val instance = Room.databaseBuilder(context.applicationContext,ScheduleDatabase::class.java,"ScheduleDatabase").build()
                INSTANCE = instance
                instance
            }
        }
    }
}