package com.example.myhealth

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myhealth.model.Schedule

@Composable
fun HelpPage(navController: NavController) {
    HelpUI(navController)
}

@Composable
fun HelpUI(navController:NavController) {
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
            val scrollState = rememberScrollState()
            HelpToolBar(navController = navController)
//        Spacer(modifier = Modifier.height(200.dp))
            // Inner Box with padding
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xD3191B19))
                    .verticalScroll(scrollState)
            ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Column(){
                            Image(painterResource(id = R.drawable.water), contentDescription = null,
                                modifier = Modifier
                                    .size(210.dp)
                                    .scale(1.5f)
                                    )

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Divider(
                            color = Color.White,
                            thickness = 1.dp
                            // modifier = Modifier.background(Color.White)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(modifier = Modifier.padding(7.dp)) {
                            Text(text = "The body constantly loses water throughout the day, mostly through urine and sweat but also from regular body functions like breathing. To prevent dehydration, you need to get plenty of water from drink and food every day.\n" +
                                    " There are many different opinions on just how much water you should be drinking every day.\n" +
                                    " Health experts commonly recommend eight 8-ounce glasses, which equals about 2 liters, or half a gallon a day. This is called the 8×8 rule and is very easy to remember.\n" +
                                    "\n" +
                                    "However, some experts believe that you need to sip on water constantly throughout the day, even when you’re not thirsty.\n" +
                                    " As with most things, this depends on the individual. Many factors (both internal and external) ultimately affect how much water you need.\n" +
                                    "      You might need more water than someone else. How much water you need also depends on:\n" +
                                    "Where you live. You will need more water in hot, humid, or dry areas. You’ll also need more water if you live in the mountains or at a high altitude (3Trusted Source).\n" +
                                    "Your diet. If you drink a lot of coffee and other caffeinated beverages you might lose more water through extra urination. You will likely also need to drink more water if your diet is high in salty, spicy, or sugary foods. Or, more water is necessary if you don’t eat a lot of hydrating foods that are high in water like fresh or cooked fruits and vegetables.\n" +
                                    "The temperature or season. You may need more water in warmer months than cooler ones due to perspiration.\n" +
                                    "Your environment. If you spend more time outdoors in the sun or hot temperatures or in a heated room, you might feel thirstier faster.\n" +
                                    "How active you are. If you are active during the day or walk or stand a lot, you’ll need more water than someone who’s sitting at a desk. If you exercise or do any intense activity, you will need to drink more to cover water loss.\n" +
                                    "Your health. If you have an infection or a fever, or if you lose fluids through vomiting or diarrhea, you will need to drink more water. If you have a health condition like diabetes you will also need more water. Some medications like diuretics can also make you lose water.\n" +
                                    "Pregnant or breastfeeding. If you’re pregnant or nursing your baby, you’ll need to drink extra water to stay hydrated. Your body is doing the work for two (or more), after all.")
                            // Text elements displaying name, age, weight, water consumed, and goal set

                        }
                    }

            }
        }
    }
}
@Composable
fun HelpToolBar(navController: NavController){
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
                text = "Help",
                color = Color.White,
                lineHeight = 1.1.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .requiredWidth(width = 231.dp))
        }
    }
}