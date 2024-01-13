package com.example.myhealth.ViewModel.ScheduleViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myhealth.Repository.ScheduleRepository


class ScheduleViewModelFactory(val repository: ScheduleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScheduleViewModel(repository) as T
    }
}