package com.menna.caffeine.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.R
import com.menna.caffeine.ui.theme.urbanisation


@Composable

fun SizeSelectorHeader(
    onclickPrevious: () -> Unit,
    text: String
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        IconCircleButton(
            onclickPrevious,
            R.drawable.arrow_left
        )
        Text(
            text = text,
            color = Color(0xDE1F1F1F),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = urbanisation,
            letterSpacing = .25.sp,
            lineHeight = 24.sp,
        )
    }
}
