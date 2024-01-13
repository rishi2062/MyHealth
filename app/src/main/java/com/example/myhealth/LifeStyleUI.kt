package com.example.myhealth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.navArgument
import com.example.myhealth.Module.UpsertData
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel

@Composable
fun LifeStyleUI(navController: NavController, viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    )
    {
        Column(
            modifier = Modifier
                .requiredWidth(width = 411.dp)
        ) {
//        Image(
//            painter = painterResource(id = R.drawable.dark),
//            contentDescription = "Atoms/System bar/Dark",
//            colorFilter = ColorFilter.tint(Color.White),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp,
//                    vertical = 7.dp))
            LifeToolBar(navController)
            LifeStyleBody(navController, viewModel)
        }
    }

}


@Composable
fun LifeToolBar(navController: NavController) {
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
                    }
            )

            Text(
                text = "Lifestyle",
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

@Composable
fun LifeStyleBody(navController: NavController, viewModel: MainViewModel) {
    //val scrollState = rememberScrollState(0)
    Column(
        verticalArrangement = Arrangement.spacedBy(29.dp, Alignment.Top),
        modifier = Modifier
            .requiredWidth(width = 408.dp)
            .padding(all = 20.dp)
    ) {
        LifeStyleContent(navController, viewModel)
    }
}

@Composable
fun LifeStyleContent(navController: NavController, viewModel: MainViewModel) {
    var lifeStyleOption by remember{
        viewModel.lifeStlye
    }
    var colorNoActivty by remember{
        mutableStateOf(Color.Transparent)
    }
    var colorLightActivty by remember{
        mutableStateOf(Color.Transparent)
    }
    var colorModActivty by remember{
        mutableStateOf(Color.Transparent)
    }
    var colorVeryActivty by remember{
        mutableStateOf(Color.Transparent)
    }
    when(lifeStyleOption){
        0 -> {
            colorNoActivty = Color.Green
            colorLightActivty = Color.Transparent
            colorModActivty = Color.Transparent
            colorVeryActivty = Color.Transparent
        }
        1 -> {
            colorLightActivty = Color.Green
            colorNoActivty = Color.Transparent
            colorModActivty = Color.Transparent
            colorVeryActivty = Color.Transparent
        }
        2 -> {
            colorModActivty = Color.Green
            colorLightActivty = Color.Transparent
            colorNoActivty = Color.Transparent
            colorVeryActivty = Color.Transparent
        }
        3 -> {
            colorVeryActivty = Color.Green
            colorLightActivty = Color.Transparent
            colorModActivty = Color.Transparent
            colorNoActivty = Color.Transparent
        }
        else -> lifeStyleOption = 4
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Please select the activity based on your lifestyle",
            color = Color.White,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.clickable {
            lifeStyleOption = 0
        }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.Black.copy(alpha = 0.38f))
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Check circle",
                    tint = colorNoActivty,
                    modifier = Modifier
                        .requiredSize(size = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Little or No Activity",
                        color = Color.White,
                        lineHeight = 1.4.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Mostly sitting through the day ",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }
        Box(modifier = Modifier.clickable {
                lifeStyleOption = 1
        }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.Black.copy(alpha = 0.38f))
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Check circle",
                    tint = colorLightActivty,
                    modifier = Modifier
                        .requiredSize(size = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Lightly Active",
                        color = Color.White,
                        lineHeight = 1.4.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .weight(weight = 1f)
                    )

                }
                Text(
                    text = "Mostly standing through the day",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Box(modifier = Modifier.clickable {
            lifeStyleOption = 2
        }) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.Black.copy(alpha = 0.38f))
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Check circle",
                    tint = colorModActivty,
                    modifier = Modifier
                        .requiredSize(size = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Moderately Active",
                        color = Color.White,
                        lineHeight = 1.4.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Mostly walking or doing physical activities through the day ",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Box(modifier = Modifier.clickable {
            lifeStyleOption = 3
        }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.Black.copy(alpha = 0.38f))
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Check circle",
                    tint = colorVeryActivty,
                    modifier = Modifier
                        .requiredSize(size = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Very Active",
                        color = Color.White,
                        lineHeight = 1.4.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = "Mostly doing heavy physical activities through the day",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Row{
            Button(onClick = {
                UpsertData(viewModel)
                navController.navigate("MainScreen")
            },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(2.dp, Color.LightGray),
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
}



