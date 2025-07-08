package com.menna.caffeine.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.menna.caffeine.R

@Composable
fun GhostImage(modifier: Modifier= Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 21f,
        targetValue = -21f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val shadowAlpha = ((offsetY + 21f) / 42f).coerceIn(0.6f, 1f)
    Column(
        modifier = modifier.padding(top=12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.coffee_ghost),
            contentDescription = "Ghost",
            modifier = Modifier.width(244.dp)
                .graphicsLayer {
                    translationY = offsetY
                },
        )
        Image(
            painter = painterResource(id = R.drawable.ghost_shadow),
            contentDescription = "Ghost Shadow",
            modifier = Modifier.width(177.30667114257812.dp)
                .graphicsLayer {
                    alpha = shadowAlpha
                }
        )
    }
}

@Preview
@Composable
fun Preview() {
    GhostImage()
}