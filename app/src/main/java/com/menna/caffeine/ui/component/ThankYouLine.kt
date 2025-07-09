package com.menna.caffeine.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.R
import com.menna.caffeine.ui.theme.singlet

@Composable
fun ThankYouLine(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.elements),
            contentDescription = null,
            tint =  Color(0xff7C351B),
            modifier = Modifier
                .size(32.dp)
                .padding(end = 6.dp)
        )
        Text(
            text = "More Espresso, Less Depresso",
            color =  Color(0xff7C351B),
            fontFamily = singlet,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.25.sp,
        )
        Icon(
            painter = painterResource(id = R.drawable.elements),
            contentDescription = null,
            tint =  Color(0xff7C351B),
            modifier = Modifier
                .size(32.dp)
                .padding(start = 6.dp)
        )
    }
}
