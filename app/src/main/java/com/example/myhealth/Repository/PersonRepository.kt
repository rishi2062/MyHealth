package com.example.myhealth.Repository

import androidx.lifecycle.LiveData
import com.example.myhealth.Dao.PersonDao
import com.example.myhealth.model.Person

class PersonRepository(val personDao : PersonDao) {
    suspend fun insertMyInfo(person : Person) = personDao.insertMyInfo(person)
    suspend fun updateMyInfo(person : Person) = personDao.updateMyInfo(person)
    suspend fun deleteMyInfo(person : Person) = personDao.deleteMyInfo(person)
    fun getMyInfo() : LiveData<List<Person>> {
        return personDao.getMyInfo()
    }
    fun deleteData() = personDao.deleteData()
    suspend fun getDatabaseInfo() : Int = personDao.getDatabaseDetails()
}