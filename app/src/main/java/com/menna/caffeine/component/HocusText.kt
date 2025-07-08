package com.menna.caffeine.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.R
import com.menna.caffeine.ui.theme.sniglet
import kotlinx.coroutines.delay

@Composable
fun HocusText(
    modifier: Modifier= Modifier
){
    Box(
        modifier = modifier
            .padding(top = 8.dp)
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hocus",
                color = Color.Black,
                fontFamily = sniglet,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = .25.sp,
            )
            Text(
                text = "Pocus",
                color = Color.Black,
                fontFamily = sniglet,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = .25.sp,
            )
            Text(
                text = "I Need Coffee",
                color = Color.Black,
                fontFamily = sniglet,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = .25.sp,
            )
            Text(
                text = "to Focus",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Black,
                fontFamily = sniglet,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = .25.sp,
            )
        }
        BlinkingStar(
            modifier = Modifier
                .padding(start = 10.dp, top = 65.dp)
                .size(16.dp)
        )
        BlinkingStar(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .size(16.dp)
                .offset(x = 1.dp)
        )
        BlinkingStar(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .offset(x = 18.dp)
                .size(16.dp)
        )
    }
}

@Composable
private fun BlinkingStar(modifier: Modifier = Modifier) {
    var isDefault = remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            isDefault.value = !isDefault.value
        }
    }

    val image = if (isDefault.value) {
        painterResource(id = R.drawable.default_star)
    } else {
        painterResource(id = R.drawable.variant_star)
    }

    Image(
        painter = image,
        contentDescription = "Blinking Star",
        modifier = modifier.size(16.dp)
    )
}
