package com.example.myhealth
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
@Composable
fun OfflineMode(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.offlinebackground),
                contentScale = ContentScale.FillBounds
            )
    ){
        StorageComponent()
    }

}

@Composable
fun StorageComponent(modifier: Modifier = Modifier){
    Column {
        Row {
            Text(
                text = "Storage",
                color = Color.White,
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 12.494437217712402.sp),
                modifier = modifier)
        }
        Row{
            var sliderValues by remember {
                mutableStateOf(1f..100f)
            }
            var sliderPosition by remember { mutableStateOf(0f) }
           // Slider(value = , onValueChange = )
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                onValueChangeFinished = { },
                modifier = modifier
                    .requiredWidth(width = 170.dp)
                    .requiredHeight(height = 8.dp)
                    .clip(shape = RoundedCornerShape(3.5698392391204834.dp))
                    .background(color = Color(0xff00ea5e)))
        }
        Row{
            Column {
                Text(
                    text = "Used :  1.4GB",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 10.709517478942871.sp))
                Box(
                    modifier = Modifier
                        .requiredSize(size = 11.dp)
                        .background(color = Color(0xff00ea5e)))
            }
            Column {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 11.dp)
                        .background(color = Color(0xffe0e0e0)))
                Text(
                    text = "Free :  2.1GB",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 10.709517478942871.sp))
            }
        }
    }

}



@Composable
fun Frame1574(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(21.419034957885742.dp, Alignment.Start),
        modifier = modifier
            .requiredWidth(width = 350.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.139678478240967.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Used :  1.4GB",
                color = Color.White,
                style = TextStyle(
                    fontSize = 10.709517478942871.sp))
            Box(
                modifier = Modifier
                    .requiredSize(size = 11.dp)
                    .background(color = Color(0xff00ea5e)))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.139678478240967.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 11.dp)
                    .background(color = Color(0xffe0e0e0)))
            Text(
                text = "Free :  2.1GB",
                color = Color.White,
                style = TextStyle(
                    fontSize = 10.709517478942871.sp))
        }
    }
}
