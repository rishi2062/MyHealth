package com.example.myhealth.Repository

import androidx.lifecycle.LiveData
import com.example.myhealth.Dao.ScheduleDao
import com.example.myhealth.model.Schedule



class ScheduleRepository(val scheduleDao : ScheduleDao) {
    suspend fun insertMySchedule(schedule : Schedule) = scheduleDao.insertMySchedule(schedule)
    suspend fun updateMySchedule(schedule : Schedule) = scheduleDao.updateMySchedule(schedule)
    suspend fun deleteMySchedule(schedule : Schedule) = scheduleDao.deleteMySchedule(schedule)
    fun getMySchedule() : LiveData<List<Schedule>> {
        return scheduleDao.getMySchedule()
    }
    suspend fun getDatabaseInfo() : Int = scheduleDao.getDatabaseDetails()
}