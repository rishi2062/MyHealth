package com.example.myhealth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkEnvironment(navController : NavController, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .requiredWidth(width = 411.dp)

        ) {
            WorkToolBar(navController)
            BodyUI(navController,viewModel)
        }
    }
}

@Composable
fun BodyUI(navController: NavController,viewModel: MainViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "Please select the environment you work in",
            color = Color.White,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth())
        OptionContent(navController = navController,viewModel)
    }
}

@Composable
fun OptionContent(navController: NavController,viewModel: MainViewModel) {
//    var workLife by remember{
//        viewModel.workLife
//    }
    var indoorChecked by remember{
        viewModel.indoorChecked
    }
    var outdoorChecked by remember{
        viewModel.outdoorChecked
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .requiredHeight(height = 132.dp)
                .weight(weight = 0.5f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(0xff959393).copy(alpha = 0.4f))
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .requiredWidth(width = 182.dp)
                    .requiredHeight(height = 132.dp)
                    .padding(all = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.clickable {
                                indoorChecked = Color.Green
                                outdoorChecked = Color.Transparent
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.indoorwork),
                                contentDescription = "Indoor work",
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 10.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(90.dp,Alignment.End),
                                verticalAlignment = Alignment.Top) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        16.dp,
                                        Alignment.Top
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Indoor",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 1.4.em,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        16.dp,
                                        Alignment.Top
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "Check circle",
                                        tint = indoorChecked,
                                        modifier = Modifier
                                            .requiredSize(size = 24.dp))
                                }
                            }
                        }

                    }
                }
            }

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .requiredHeight(height = 133.dp)
                .weight(weight = 0.5f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(0xff959393).copy(alpha = 0.44f))
                .padding(all = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.clickable {
                        outdoorChecked = Color.Green
                        indoorChecked = Color.Transparent
                    }){
                        Image(
                            painter = painterResource(id = R.drawable.outdoorwork),
                            contentDescription = "Outdoor work",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(90.dp,Alignment.End),
                            verticalAlignment = Alignment.Top) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    16.dp,
                                    Alignment.Top
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Outdoor",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 1.4.em,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    16.dp,
                                    Alignment.Top
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Check circle",
                                    tint = outdoorChecked,
                                    modifier = Modifier
                                        .requiredSize(size = 24.dp))
                            }
                        }
                    }
                }
            }
        }
    }
    Row{
        Button(onClick = {
            navController.navigate("LifeStyle")
        },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(3.dp, Color.LightGray),
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Filled.NearMe,
                contentDescription = "Next Page",
                tint = Color(0xB909E1E7)
            )
        }
    }
}

@Composable
fun WorkToolBar(navController: NavController){
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
                        navController.navigate("GetMyAge")
                    }
            )

            Text(
                text = "Work",
                color = Color.White,
                lineHeight = 1.1.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .requiredWidth(width = 231.dp))
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