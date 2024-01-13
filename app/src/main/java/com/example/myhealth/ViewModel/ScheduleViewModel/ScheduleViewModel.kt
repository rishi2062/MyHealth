package com.example.myhealth.ViewModel.ScheduleViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.Repository.ScheduleRepository
import com.example.myhealth.model.Person
import com.example.myhealth.model.Schedule
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleViewModel(val repo: ScheduleRepository) : ViewModel() {
    var quantitySelected = mutableStateOf(0)
    var beverageSelected = mutableStateOf(0)
    var weatherPermission = mutableStateOf(true)
    var recordCount = mutableStateOf(0)


    init{
        recordCount.value = getRecords()
        loadMySchedule()
    }
    fun loadMySchedule() : LiveData<List<Schedule>> = repo.getMySchedule()
    fun getRecords() : Int{
        viewModelScope.launch {
            recordCount.value = repo.getDatabaseInfo()
            return@launch
        }
        Log.d("MineRecord", recordCount.toString())
        return recordCount.value
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun insertMySchedule(schedule: Schedule) = GlobalScope.launch {
        repo.insertMySchedule(schedule)
    }

    fun deleteMyDetail(schedule: Schedule) = viewModelScope.launch {
        repo.deleteMySchedule(schedule)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateMyDetail(schedule: Schedule) = GlobalScope.launch {
        repo.updateMySchedule(schedule)
    }
}