package com.menna.caffeine.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.menna.caffeine.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_icon),
            contentDescription = "Header Icon",
            modifier = Modifier
                .align(Alignment.Top)
                .size(48.dp)
        )
        Box(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color = Color(0xFFF5F5F5))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_button),
                contentDescription = "Add icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
            )
        }


    }
}