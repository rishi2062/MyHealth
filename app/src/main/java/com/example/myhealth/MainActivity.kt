package com.example.myhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myhealth.Database.PersonDatabase
import com.example.myhealth.Database.ScheduleDatabase
import com.example.myhealth.Repository.PersonRepository
import com.example.myhealth.Repository.ScheduleRepository
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel
import com.example.myhealth.ViewModel.MainViewModel.MainViewModelFactory
import com.example.myhealth.ViewModel.ScheduleViewModel.ScheduleViewModel
import com.example.myhealth.ViewModel.ScheduleViewModel.ScheduleViewModelFactory
import com.example.myhealth.model.Person
import com.example.myhealth.model.Schedule
import com.example.myhealth.ui.theme.MyHealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val db = PersonDatabase.getDatabase(this)
                    val repo = PersonRepository(db.personDao())
                    val scheduleDb = ScheduleDatabase.getDatabase(this)
                    val scheduleRepo = ScheduleRepository(scheduleDb.scheduleDao())
                    val mainViewModel = ViewModelProvider(
                        this,
                        MainViewModelFactory(repo)
                    )[MainViewModel::class.java]
                    val scheduleViewModel = ViewModelProvider(
                        this,
                        ScheduleViewModelFactory(scheduleRepo)
                    )[ScheduleViewModel::class.java]
                    val person by mainViewModel.loadMyInfo().observeAsState()
                    person?.let {
                        NavigationManange(
                            navController = navController, mainViewModel = mainViewModel,
                             scheduleViewModel
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun NavigationManange(navController: NavHostController, mainViewModel: MainViewModel,scheduleViewModel: ScheduleViewModel) {
    if (mainViewModel.getRecords() == 0) {
        NavHost(navController = navController, startDestination = "WelcomeScreen") {
            composable("GetMyInfo") {
                GiveInfo(navController = navController, mainViewModel)
            }
            composable("WelcomeScreen") {
                WelcomeUI(navController = navController)
            }
            composable("MainScreen") {
                MainUI(navController = navController, mainViewModel, scheduleViewModel = scheduleViewModel)
            }
            composable("WorkEnvironment") {
                WorkEnvironment(navController, mainViewModel)
            }
            composable("LifeStyle") {
                LifeStyleUI(navController = navController, mainViewModel)
            }
            composable("GetMyAge") {
                GetAge(navController = navController, mainViewModel)
            }
            composable("GetWeight") {
                GetWeight(navController = navController, mainViewModel)
            }
            composable("GetGoal") {
                GetGoal(navController = navController, mainViewModel)
            }
            composable("MyProfileUI") {
                MyProfileUI(navController = navController, mainViewModel,scheduleViewModel)
            }
            composable("HelpUI") {
                HelpUI(navController = navController)
            }
        }
    } else {
        NavHost(navController = navController, startDestination = "MainScreen") {
            composable("MainScreen") {
                MainUI(
                    navController = navController,
                    mainViewModel,
                    scheduleViewModel
                )
            }
            composable("MyProfileUI") {
                MyProfileUI(navController = navController, mainViewModel, scheduleViewModel)
            }
            composable("HelpUI") {
                HelpUI(navController = navController)
            }
        }
    }
}

