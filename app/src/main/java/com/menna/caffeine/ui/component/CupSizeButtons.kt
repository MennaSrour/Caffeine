package com.menna.caffeine.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.ui.screen.modifier.dropShadow
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState.CupSize
import com.menna.caffeine.ui.theme.urbanisation


@Composable
fun CupSizeButtons(
    selectedSize: CupSize,
    onSizeSelected: (CupSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(50.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CupSize.entries.forEach { size ->
            val isSelected = selectedSize == size

            val targetColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                animationSpec = tween(durationMillis = 200)
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xDEFFFFFF) else Color(0x991F1F1F),
                animationSpec = tween(durationMillis = 200)
            )

            Button(
                onClick = { onSizeSelected(size) },
                modifier = Modifier
                    .width(40.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = if (isSelected) Color(0x80B94B23) else Color(0xFFF5F5F5),
                        alpha = if (isSelected) 0.5f else 0.3f,
                        blur = 16.dp,
                        offsetX = 0.dp,
                        offsetY = 6.dp
                    ),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = targetColor
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = size.name.first().toString(),
                    color = textColor,
                    fontFamily = urbanisation,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = .25.sp,
                )
            }
        }
    }
}