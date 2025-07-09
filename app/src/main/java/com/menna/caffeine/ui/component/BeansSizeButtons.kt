package com.menna.caffeine.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.R
import com.menna.caffeine.ui.screen.modifier.dropShadow
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState.BeansSize
import com.menna.caffeine.ui.theme.urbanisation

@Composable
fun BeansSizeButtons(
    selectedSize: BeansSize,
    onSizeSelected: (BeansSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier
                .height(56.dp)
                .background(
                    Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BeansSize.entries.forEach { size ->
                val isSelected = selectedSize == size

                val targetColor by animateColorAsState(
                    targetValue = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                    animationSpec = tween(durationMillis = 200)
                )

                Button(
                    onClick = { onSizeSelected(size) },
                    modifier = Modifier
                        .size(40.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.elements),
                        contentDescription = "Seed Icon",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
        Row(
            modifier = modifier
                .width(152.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("Low", "Medium", "High").forEach { label ->
                Text(
                    text = label,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.25.sp,
                    color = Color(0x991F1F1F),
                    fontFamily = urbanisation
                )
            }
        }
    }
}