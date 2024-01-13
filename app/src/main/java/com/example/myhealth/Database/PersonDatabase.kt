package com.example.myhealth.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myhealth.Dao.PersonDao
import com.example.myhealth.model.Person

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao() : PersonDao

    companion object{
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            return INSTANCE ?: synchronized(true){
                val instance = Room.databaseBuilder(context.applicationContext,PersonDatabase::class.java,"PersonDatabase").build()
                INSTANCE = instance
                instance
            }
        }
    }
}