package com.menna.caffeine.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.ui.theme.urbanisation

@Composable
fun TextIconButton(
    onClickNext: () -> Unit,
    text: String,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickNext,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1F1F1F)
        ),
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp,
            hoveredElevation = 10.dp
        ),
        modifier = modifier
            .wrapContentSize(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = text,
                color = Color(0xDEFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = urbanisation,
                letterSpacing = .25.sp,
                lineHeight = 16.sp,
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = " icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }


}
