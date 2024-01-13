package com.example.myhealth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel
import com.example.myhealth.ViewModel.ScheduleViewModel.ScheduleViewModel
import com.example.myhealth.model.Person
import com.example.myhealth.model.Schedule

@Composable
fun MyProfileUI(navController: NavController, viewModel: MainViewModel,scheduleViewModel: ScheduleViewModel) {
    MyInfoUI(navController = navController, viewModel = viewModel, scheduleViewModel = scheduleViewModel)
}



@Composable
fun MyInfoUI(navController: NavController,viewModel: MainViewModel,scheduleViewModel: ScheduleViewModel) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column {

            ProfileToolBar(navController = navController)
//        Spacer(modifier = Modifier.height(200.dp))
            // Inner Box with padding
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0x3C1A3119))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(Color(0xfffffff))
                ) {
                    Column {
                        Column {
                            val person by viewModel.loadMyInfo().observeAsState()
                            person?.let {
                                // Text elements displaying name, age, weight, water consumed, and goal set
                                InfoRow("Name", it[0].name)
                                InfoRow("Age", it[0].age)
                                InfoRow("Weight", it[0].weight)
                                InfoRow("Water Consumed", it[0].waterConsumed)
                                InfoRow("Goal Set", it[0].goal)
                            }
                        }

                        Divider(
                            color = Color.White,
                            thickness = 1.dp
                            // modifier = Modifier.background(Color.White)
                        )
                        Column {
                            val schedule by scheduleViewModel.loadMySchedule().observeAsState()
                            if(scheduleViewModel.getRecords()==0){
                                ScheduleInfo(schedule = Schedule(1,4,4,false))
                            }
                            else {
                                schedule?.let {
                                    ScheduleInfo(schedule = it[0])
                                }
                            }
                            // Text elements displaying name, age, weight, water consumed, and goal set


                        }
                        Column(verticalArrangement = Arrangement.Bottom) {
//                            Button(onClick = {
//                                viewModel.DeleteData()
//                              //  navController.popBackStack()
//                                navController.navigate("WelcomeUI")
//
//                                             }, modifier = Modifier.fillMaxWidth(),
//                                colors = ButtonDefaults.buttonColors(
//                                    Color(0xFFF0EFEF)
//                                ),
//                                border = BorderStroke(2.dp, Color.Red)
//                                ) {
//                                Text(text = "Delete My Account")
//                            }
                        }
                    }

                }
            }
        }
    }
}



@Composable
fun ScheduleInfo(schedule: Schedule){
    val quantity = schedule.quantity
    var quantityValue = "Not Scheduled"
    quantityValue = when(quantity){
        0 -> "200 ml"
        1 -> "500 ml"
        2 -> "1000 ml"
        3 -> "2000 ml"
        else -> "Not Scheduled"
    }
    val beverages = schedule.beverageSelected
    var beverageValue = ""
    beverageValue = when(beverages){
        0 -> "Water"
        1 -> "Coconut"
        2 -> "Milk"
        3 -> "Fresh Juice"
        else -> "Not Scheduled"
    }
    InfoRow("Water Quantity", quantityValue)
    InfoRow("Beverage Selected", beverageValue)
    InfoRow("Weather Permission Availed", schedule.weatherPermission.toString())
    
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ProfileToolBar(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 56.dp)
            .padding(all = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(weight = 1f)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Leading Icon l Use High Emphasis",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 6.dp, end = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Text(
                text = "Profile",
                color = Color.White,
                lineHeight = 1.1.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .requiredWidth(width = 231.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.help),
            contentDescription = "contact_support",
            modifier = Modifier
                .size(30.dp)
                .padding(end = 8.dp)
                .clickable {
                    navController.navigate("HelpUI")
                })
    }
}