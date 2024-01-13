package com.example.myhealth


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myhealth.Module.UpdateData
import com.example.myhealth.ViewModel.MainViewModel.MainViewModel
import com.example.myhealth.ViewModel.ScheduleViewModel.ScheduleViewModel
import com.example.myhealth.model.Person
import com.example.myhealth.model.Schedule


@Composable
fun MainUI(
    navController: NavController,
    viewModel: MainViewModel,
    scheduleViewModel: ScheduleViewModel
) {

    val person by viewModel.loadMyInfo().observeAsState()
    person?.let {
        WaterBottomSheet(navController,viewModel,scheduleViewModel,it[0])
    }
    //viewModel.insertMyDetail(person)


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterBottomSheet(
    navController: NavController,
    viewModel: MainViewModel,
    scheduleViewModel: ScheduleViewModel,
    person: Person
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(scaffoldState = bottomSheetState,
        sheetPeekHeight = 200.dp,
        sheetContainerColor = Color.Unspecified,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.background),
                        contentScale = ContentScale.FillBounds,
                        alpha = 1f,
                    )
                    .padding(5.dp)
                    .weight(1f)
            ) {
                WaterQuantity(modifier = Modifier,scheduleViewModel)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 1.dp)
                )
                BeverageState(scheduleViewModel)
                DaysSlider()
                WeatherNotificationAlert(modifier = Modifier,scheduleViewModel)
                ScheduleButton(scheduleViewModel)
            }
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds,
                    alpha = .8f,
                )
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0x2524262C))
                ){
                    Row(
                        horizontalArrangement = Arrangement.End){
                        Column(horizontalAlignment = Alignment.Start) {
                            Row {
                                Text(text = "Hello",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif
                                )
                                Text(text = " ${person.name}",
                                    fontSize = 40.sp,
                                    fontFamily = FontFamily.Cursive
                                )
                            }
                            Row {
                                Text(text = "How is your day...",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif)
                            }
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Button(onClick = {
                                navController.navigate("MyProfileUI")
                            },
                                modifier = Modifier
                                    .padding(start = 100.dp, top = 10.dp)
                                    .size(60.dp),
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                border = BorderStroke(3.dp, Color.LightGray),
                                shape = CircleShape

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Person",
                                    tint = Color(0xB909E1E7),
                                    modifier = Modifier.scale(2.0f)
                                )
                            }
                        }
                    }

                }
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(top = 40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0x9E1F1818))
                )
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box() {
                            SliderBox(viewModel = viewModel,person)
                        }
                        GoalData(viewModel = viewModel, person = person)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .background(color = Color(0x2524262C), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.info),
                                contentDescription = "Icon",
                                modifier = Modifier.scale(1.3f),
                                tint = Color.Unspecified
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.Start,

                            ) {
                            Text(
                                text = "Tie it into a routine. Drink a glass of water every time you brush your teeth, eat a meal or use the bathroom.",
                                color = Color.White,
                                lineHeight = 1.4.em,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }

                    }


                }
            }
        }

    }
}

@Composable
fun BeverageState(scheduleViewModel: ScheduleViewModel) {
    var beverageOption by remember{
        scheduleViewModel.beverageSelected
    }
    val defaultColor = Color(0x12504C4C).copy(alpha = 0.42f)
    var waterButton by remember {
        mutableStateOf(defaultColor)
    }
    var coconutButton by remember {
        mutableStateOf(defaultColor)
    }
    var shakeButton by remember {
        mutableStateOf(defaultColor)
    }
    var juiceButton by remember {
        mutableStateOf(defaultColor)
    }

    when(beverageOption){
        0 -> {
            waterButton = Color.Green
            coconutButton = defaultColor
            shakeButton = defaultColor
            juiceButton = defaultColor
        }
        1 -> {
            waterButton = defaultColor
            coconutButton = Color.Green
            shakeButton = defaultColor
            juiceButton = defaultColor
        }
        2 -> {
            waterButton = defaultColor
            coconutButton = defaultColor
            shakeButton = Color.Green
            juiceButton = defaultColor
        }
        3 -> {
            waterButton = defaultColor
            coconutButton = defaultColor
            shakeButton = defaultColor
            juiceButton = Color.Green
        }
        else -> beverageOption = 4
    }


    Column() {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Beverages",
                fontSize = 20.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(
            ) {
                Button(
                    onClick = {
                       beverageOption = 0
                    },
                    Modifier.height(72.dp),
                    colors = ButtonDefaults.buttonColors(waterButton)
                ) {
                    Image(
                        painterResource(id = R.drawable.milk),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Water", fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Column(
            ) {
                Button(
                    onClick = {
                        beverageOption = 1
                    },
                    Modifier.height(72.dp),
                    colors = ButtonDefaults.buttonColors(coconutButton)
                ) {
                    Image(
                        painterResource(id = R.drawable.coconut),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Coconut", modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 10.sp, fontWeight = FontWeight.Bold
                )

            }
            Column(
            ) {
                Button(
                    onClick = {
                        beverageOption = 2
                    },
                    Modifier.height(72.dp),
                    colors = ButtonDefaults.buttonColors(shakeButton)
                ) {
                    Image(
                        painterResource(id = R.drawable.shake),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Milk", modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 10.sp, fontWeight = FontWeight.Bold
                )
            }
            Column(
            ) {
                Button(
                    onClick = {
                        beverageOption = 3
                    },
                    Modifier.height(72.dp),
                    colors = ButtonDefaults.buttonColors(juiceButton)
                ) {
                    Image(
                        painterResource(id = R.drawable.fruitjuice),
                        contentDescription = null
                    )

                }
                Text(
                    text = "Fresh Juice", modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 10.sp, fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Composable
fun WaterQuantity(modifier: Modifier = Modifier,scheduleViewModel: ScheduleViewModel) {
    var quantityOption by remember{
        scheduleViewModel.quantitySelected
    }
    val defaultColor = Color(0x12504C4C).copy(alpha = 0.44f)
    var quantity200 by remember {
        mutableStateOf(defaultColor)
    }
    var quantity500 by remember {
        mutableStateOf(defaultColor)
    }
    var quantity1000 by remember {
        mutableStateOf(defaultColor)
    }
    var quantity2000 by remember {
        mutableStateOf(defaultColor)
    }

    when(quantityOption){
        0 -> {
            quantity200 = Color.Green
            quantity500 = defaultColor
            quantity1000 = defaultColor
            quantity2000 = defaultColor
        }
        1 -> {
            quantity500 = Color.Green
            quantity200 = defaultColor
            quantity1000 = defaultColor
            quantity2000 = defaultColor
        }
        2 -> {
            quantity1000 = Color.Green
            quantity500 = defaultColor
            quantity200 = defaultColor
            quantity2000 = defaultColor
        }
        3 -> {
            quantity2000 = Color.Green
            quantity500 = defaultColor
            quantity1000 = defaultColor
            quantity200 = defaultColor
        }
        else -> quantityOption = 4
    }

    Column() {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Quantity",
                fontSize = 20.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(
            ) {
                Button(
                    onClick = {
                        quantityOption = 0
                    },
                    Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(quantity200)
                ) {
                    Text(
                        text = "200 ml", fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }

            }
            Column(
            ) {
                Button(
                    onClick = {
                        quantityOption = 1
                    },
                    Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(quantity500)
                ) {
                    Text(
                        text = "500 ml",
                        color = Color.White,
                        fontSize = 10.sp, fontWeight = FontWeight.Bold
                    )
                }


            }
            Column(
            ) {
                Button(
                    onClick = {
                        quantityOption = 2
                    },
                    Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(quantity1000)
                ) {
                    Text(
                        text = "1000 ml",
                        color = Color.White,
                        fontSize = 8.sp, fontWeight = FontWeight.Bold
                    )
                }

            }
            Column(
            ) {
                Button(
                    onClick = {
                        quantityOption = 3
                    },
                    Modifier.height(80.dp),
                    colors = ButtonDefaults.buttonColors(quantity2000)
                ) {
                    Text(
                        text = "2000 ml",
                        color = Color.White,
                        fontSize = 8.sp, fontWeight = FontWeight.Bold
                    )
                }


            }
        }
    }
}

@Composable
fun WeatherNotificationAlert(modifier: Modifier = Modifier,scheduleViewModel: ScheduleViewModel) {
    var weatherPerm by remember {
        scheduleViewModel.weatherPermission
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0x8B24262C), RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Icon",
                    tint = Color(0xff4caf50),
                    modifier = Modifier.scale(1.3f)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = "Weather",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "There will be addition of 500 ml to 1 Litre \nof water to your daily intake based on the \nweather temperature.",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }
            Column(
            ) {
                //var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = weatherPerm,
                    onCheckedChange = {
                        weatherPerm = it
                    },
                    thumbContent = if (weatherPerm) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),

                                )
                        }
                    } else {
                        null
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF4CAF50),
                        checkedTrackColor = Color(color = 0x2338E22F),
                        uncheckedThumbColor = Color.DarkGray,
                        uncheckedTrackColor = Color.Transparent,
                    ),
                    modifier = Modifier.scale(.8f)
                )
            }
        }


    }
}

@Composable
fun ScheduleButton(scheduleViewModel: ScheduleViewModel) {
    var quantity by remember {
        scheduleViewModel.quantitySelected
    }
    var beverages by remember {
        scheduleViewModel.beverageSelected
    }
    var weatherPerm by remember {
        scheduleViewModel.weatherPermission
    }
    Button(
        onClick = {
                  scheduleViewModel.insertMySchedule(Schedule(1,quantity,beverages,weatherPerm))
            },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RectangleShape),
        colors = ButtonDefaults.buttonColors(Color(0xff4caf50)),
        shape = RoundedCornerShape(10.dp)

    ) {
        Icon(
            imageVector = Icons.Filled.Alarm,
            contentDescription = "Cart button icon",
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )
        Text(
            text = "Schedule", modifier = Modifier.padding(start = 10.dp),
            color = Color.White
        )
    }
}

@Composable
fun DaysSlider() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = Color(0x8B24262C), RoundedCornerShape(10.dp))
            .padding(6.dp)
            .fillMaxWidth()

    ) {
        var sliderPosition by remember { mutableStateOf(0f) }
        Column {
            Row {
                Text(text = "Download Data For : ")
                Text(
                    text = "${sliderPosition.toInt()} days",
                    color = Color(0xFF0CCD4E)
                )
            }
            Row {
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    onValueChangeFinished = { },
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Green,
                        activeTrackColor = Color.Green,
                        inactiveTrackColor = Color.LightGray,
                    ),
                    steps = 31,
                    valueRange = 0f..31f
                )

            }
        }
    }
}



@Composable
fun circularBox(changedValue: String) {
    Box(
        Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color(0xFF343144))
            .padding(0.dp),
    ) {
        middleColumn(Modifier.align(Alignment.Center), changedValue)
    }
}


@Composable
fun middleColumn(mods: Modifier, changedValue: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = mods.padding(15.dp)
    ) {
        Row() {
            // var tempValue = "16.00"
            Text(
                text = changedValue,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                //fontFamily = appFontFamily,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 12.5.dp)
                    .align(Alignment.CenterVertically)
                //color = Color.White,
            )
            Text(
                text = "ml",
                fontSize = 10.sp,
                // fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .align(Alignment.Top)
                //color = Color.White,
            )
        }
        Text(
            text = "Total ",
            color = Color.LightGray,
            fontSize = 11.sp,
            //fontFamily = appFontFamily,
            textAlign = TextAlign.Center
            //color = Color.White,
        )
        Text(
            text = "Consumed",
            color = Color.LightGray,
            fontSize = 11.sp,
            // fontFamily = appFontFamily,
            textAlign = TextAlign.Center
            //color = Color.White,
        )
    }
}


@Composable
fun SliderBox(viewModel: MainViewModel,person: Person) {

    var newValue by remember{
        viewModel.waterConsumed
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularSlider(
            modifier = Modifier
                .size(230.dp)
                .padding(5.dp),
        ) {
            Log.d("Original VAL", "${it * 180}")
            var sliderPercentage = it
            var currValue = 0 + (sliderPercentage * 10000)
            newValue = String.format("%.0f", currValue)
            // mainViewModel.tempValue.value = roundedTempValue
            viewModel.waterConsumed.value = newValue
            Log.d("progress", "$newValue ml")
        }
        circularBox(person.waterConsumed)
        UpdateData(viewModel = viewModel)
        Text(
            text = "0 ml",
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 50.dp)
            //color = Color.White,
        )
        Text(
            text = "10 l",
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 50.dp)
            //color = Color.White,
        )
    }
}

@Composable
fun GoalData(viewModel: MainViewModel,person: Person) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .requiredWidth(width = 347.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(weight = 0.33f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                viewModel.calRecommendedWater()
                Text(
                    text = "${person.waterRecommend} ml",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Recommended",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(weight = 0.33f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val gradient = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFFD9C7DF),
                        0.4f to Color(0xFF99C4EF),
                        3.0f to Color(0xFFD9C7DF)
                    )
                )
                Text(
                    text = "${person.goal} ml",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Cyan
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Goal",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(weight = 0.33f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${person.goal.toInt() - viewModel.waterConsumed.value.toInt()} ml",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Remaining",
                    color = Color.White,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

