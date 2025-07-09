package com.menna.caffeine.ui.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.ui.theme.singlet
import com.menna.caffeine.ui.theme.urbanisation

@Composable
fun PreparingText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = "Almost Done",
            fontSize = 22.sp,
            fontFamily = urbanisation,
            fontWeight = FontWeight.Bold,
            color = Color(0xCC1F1F1F),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your coffee will be finish in",
            fontSize = 16.sp,
            fontFamily = urbanisation,
            fontWeight = FontWeight.Bold,
            color = Color(0x991F1F1F),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
            ,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoffeeText("CO")
            TwoGrayCircles()
            CoffeeText("FF")
            TwoGrayCircles()
            CoffeeText("EE")
        }
    }

}

@Composable
fun CoffeeText(
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 32.sp,
            fontFamily = singlet,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF7C351B)
        )
    }
}

@Composable
fun TwoGrayCircles() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(Color(0x1F1F1F1F), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(Color(0x1F1F1F1F), shape = CircleShape)
        )
    }
}