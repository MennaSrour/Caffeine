package com.menna.caffeine.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.ui.theme.urabanist

@Composable
fun   WelcomeText(name: String) {
    Column(
        modifier=Modifier.padding(start = 16.dp)
    ){
        Text(
            text = "Good Morning",
            color = Color(0xFFB3B3B3),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = urabanist,
            letterSpacing = .25.sp,
            lineHeight = 36.sp,
        )
        Text(
            text = "$name ☀",
            color = Color(0xFF3B3B3B),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = urabanist,
            letterSpacing = .25.sp,
            lineHeight = 36.sp,
        )
        Text(
            text = "What would you like to drink today?",
            color = Color(0xCC1F1F1F),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = urabanist,
            letterSpacing = .25.sp,
            lineHeight = 16.sp,
        )
    }
}