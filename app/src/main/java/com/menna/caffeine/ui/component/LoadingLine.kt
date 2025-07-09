package com.menna.caffeine.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun LoadingLine(
    modifier: Modifier = Modifier,
    wavelength: Dp = 80.dp,
    waveHeight: Dp = 12.dp,
    color: Color = Color(0xCC1F1F1F)
) {
    val waveLengthPx = with(LocalDensity.current) { wavelength.toPx() }
    val waveHeightPx = with(LocalDensity.current) { waveHeight.toPx() }

    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "progressAnim"
    )

    Canvas(modifier = modifier) {
        val visibleWidth = size.width * animatedProgress
        val height = size.height
        val path = Path()

        var x = 0f
        path.moveTo(x, height / 2)

        while (x <= visibleWidth) {
            val y = (sin((x / waveLengthPx) * 2 * PI) * waveHeightPx).toFloat() + height / 2
            path.lineTo(x, y)
            x += 1f
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoadingLinePreview() {
    LoadingLine(
        modifier = Modifier
            .padding(top=101.dp)
            .fillMaxWidth()
            .height(32.dp)
    )
}