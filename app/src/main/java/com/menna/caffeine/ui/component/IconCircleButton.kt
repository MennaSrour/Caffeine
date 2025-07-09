package com.menna.caffeine.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconCircleButton(
    onclickButton: () -> Unit,
    icon: Int
) {
    Button(
        onClick = onclickButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape),
        contentPadding = PaddingValues(12.dp)
    ) {
        Icon(
            painter = painterResource(
                id = icon
            ),
            contentDescription = "back icon",
            tint = Color(0xDE1F1F1F),
            modifier = Modifier.size(24.dp)
        )
    }
}