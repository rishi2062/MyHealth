package com.example.myhealth.Module

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel
import com.example.myhealth.model.Person


fun UpsertData(viewModel: MainViewModel){
    val name = viewModel.myName.value
    val age = viewModel.age.value
    val workType = viewModel.LifeOption.value
    val weight = viewModel.weight.value
    val lifeStyle = viewModel.lifeStlye.value
    val waterIntake = viewModel.waterConsumed.value
    val waterRecommend = viewModel.recommendedWater.value
    val goal = viewModel.goal.value
    val person = Person(0,name,age,weight,workType,lifeStyle,waterIntake,waterRecommend.toString(),goal)
    viewModel.insertMyDetail(person)

}

@Composable
fun UpdateData(viewModel: MainViewModel){
    val PersonInfo by viewModel.loadMyInfo().observeAsState()
    PersonInfo?.let{
        val personInfo = it[0]
        val name = personInfo.name
        val age = personInfo.age
        val workType = personInfo.activityLevel
        val weight = personInfo.weight
        val lifeStyle = personInfo.lifestyle
        val waterIntake = viewModel.waterConsumed.value
        val waterRecommend = personInfo.waterRecommend
        val goal = personInfo.goal
        val person = Person(
            1,
            name,
            age,
            weight,
            workType,
            lifeStyle,
            waterIntake,
            waterRecommend.toString(),
            goal
        )
        viewModel.updateMyDetail(person)
        Log.d("UpdatedDetail", it[0].toString() + " " + waterIntake)
    }
}

//@Composable
//fun calRecommendedWater(person: Person){
//    var age = person.age
//    var recommendedWater =
//    when(age.value.toInt()) {
//        in
//        0..18 -> {
//            recommendedWater.value = 30*weight.value.toInt()
//        }
//
//        in 19..40 -> {
//            recommendedWater.value = 45*weight.value.toInt()
//        }
//
//        else ->  recommendedWater.value = 36*weight.value.toInt()
//    }
//}


