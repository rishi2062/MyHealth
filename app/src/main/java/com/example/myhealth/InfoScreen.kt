package com.example.myhealth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel

@Composable
fun GiveInfo(navController : NavController,viewModel: MainViewModel){
    GetName(navController,viewModel = viewModel)
}

@Composable
fun GetName(navController: NavController,viewModel: MainViewModel){
    var name by remember{
        viewModel.myName
    }
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.8f
            )
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var errorCheck by remember {
                mutableStateOf(checkText(name))
            }
            Row {
                Text(
                    text = "Let's Begin With Us Knowing You Better..",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,

                    ) {
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                            errorCheck = checkText(name)
                        },
                        isError = errorCheck,
                        supportingText = {
                            if (errorCheck) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Name cannot be empty",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        trailingIcon = {
                            if (errorCheck)
                                Icon(
                                    Icons.Filled.Error,
                                    "error",
                                    tint = MaterialTheme.colorScheme.error
                                )
                        },
                        label = { Text(text = "Your Name") },
                        placeholder = { Text(text = "First Name") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color(0x3BFFFFFF),
                            errorTextColor = Color.Red,
                            errorCursorColor = Color.Red
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }
            Row{
                Button(onClick = {
                    if(errorCheck){
                        showToast(context = context)
                    }
                    else {
                        navController.navigate("GetMyAge")
                    }
                },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, Color.LightGray)
                ) {
                    Icon(
                        imageVector = Icons.Filled.NearMe,
                        contentDescription = "Next Page",
                        tint = Color(0xB909E1E7)
                    )
                }
            }

        }
    }
}

fun checkText(name: String): Boolean {
    return name.isEmpty()
}
fun showToast(context : Context){
    Toast.makeText(context,"Check Name",Toast.LENGTH_LONG).show()
}

@Composable
fun GetAge(navController: NavController,viewModel: MainViewModel){
    var age by remember{
        viewModel.age
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.8f
            )
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Let's Begin With Us Knowing You Better..",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,

                    ) {
//
                    TextField(
                        value = age,
                        onValueChange = {
                            age = it
                        },
                        label = { Text(text = "Your Age") },
                        placeholder = { Text(text = "Age") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color(0x3BFFFFFF)
                        ),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.NumberPassword),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
            Row{
                Button(onClick = {
                    navController.navigate("GetWeight")
                },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, Color.LightGray)
                ) {

                    Icon(
                        imageVector = Icons.Filled.NearMe,
                        contentDescription = "Next Page",
                        tint = Color(0xB909E1E7)
                    )
                }
            }
        }
    }
}

@Composable
fun GetWeight(navController: NavController,viewModel: MainViewModel){
    var weight by remember{
        viewModel.weight
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.8f
            )
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Let's Begin With Us Knowing You Better..",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,

                    ) {
//
                    TextField(
                        value = weight,
                        onValueChange = {
                            weight = it
                        },
                        label = { Text(text = "Your Weight") },
                        placeholder = { Text(text = "Weight") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color(0x3BFFFFFF)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.NumberPassword),
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
            Row{
                Button(onClick = {
                    navController.navigate("GetGoal")
                },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, Color.LightGray)
                ) {

                    Icon(
                        imageVector = Icons.Filled.NearMe,
                        contentDescription = "Next Page",
                        tint = Color(0xB909E1E7)
                    )
                }
            }
        }
    }
}

@Composable
fun GetGoal(navController: NavController,viewModel: MainViewModel){
    var goal by remember{
        viewModel.goal
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                alpha = 0.8f
            )
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Let's Begin With Us Knowing You Better..",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,

                    ) {
//
                    TextField(
                        value = goal,
                        onValueChange = {
                            goal = it
                        },
                        label = { Text(text = "Your Goal") },
                        placeholder = { Text(text = "Set Goal") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color(0x3BFFFFFF)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.NumberPassword),
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
            Row{
                Button(onClick = {

                    navController.navigate("WorkEnvironment")
                },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, Color.LightGray)
                ) {

                    Icon(
                        imageVector = Icons.Filled.NearMe,
                        contentDescription = "Next Page",
                        tint = Color(0xB909E1E7)
                    )
                }
            }
        }
    }
}

