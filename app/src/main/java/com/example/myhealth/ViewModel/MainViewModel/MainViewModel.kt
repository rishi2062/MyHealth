package com.example.myhealth.ViewModel.MainViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.Repository.PersonRepository
import com.example.myhealth.model.Person
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainViewModel(val repo : PersonRepository) : ViewModel() {
    var myName = mutableStateOf("")
    var weight = mutableStateOf("0")
    var age = mutableStateOf("")
    var LifeOption = mutableStateOf(0)
    var lifeStlye = mutableStateOf(0)
    var waterConsumed = mutableStateOf("0")
    var recordCount = mutableStateOf(0)
    var personList = mutableStateOf<List<Person>>(listOf())
    var recommendedWater = mutableStateOf(3500)
    var goal = mutableStateOf("4000")
    var remaining = mutableStateOf(2000)
    // Boolean For WorkStyle
    var indoorChecked = mutableStateOf(Color.Transparent)
    var outdoorChecked = mutableStateOf(Color.Transparent)

    init{
        recordCount.value = getRecords()
        loadMyInfo()
       // calRecommendedWater()
    }
    fun loadMyInfo() : LiveData<List<Person>> = repo.getMyInfo()
    @OptIn(DelicateCoroutinesApi::class)
    fun insertMyDetail(person: Person) = GlobalScope.launch {

        repo.insertMyInfo(person)
    }

    fun deleteMyDetail(person: Person) = viewModelScope.launch {
        repo.deleteMyInfo(person)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateMyDetail(person:Person) = GlobalScope.launch {
        repo.updateMyInfo(person)
    }

    fun getRecords(): Int {
        viewModelScope.launch {
            recordCount.value = repo.getDatabaseInfo()
            return@launch
        }
        Log.d("MineRecord", recordCount.toString())
        return recordCount.value
    }

    fun calRecommendedWater() {
        if (age.value.isNotEmpty() && weight.value.isNotEmpty()) {
            when (age.value.toInt()) {
                in
                0..18 -> {
                    recommendedWater.value = 30 * weight.value.toInt()
                }

                in 19..40 -> {
                    recommendedWater.value = 45 * weight.value.toInt()
                }

                else -> recommendedWater.value = 36 * weight.value.toInt()
            }
        }
    }

    fun DeleteData() {
        repo.deleteData()
    }
}