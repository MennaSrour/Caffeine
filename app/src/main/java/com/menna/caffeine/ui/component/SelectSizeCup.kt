package com.menna.caffeine.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.caffeine.R
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState.BeansSize
import com.menna.caffeine.ui.theme.urbanisation


@Composable
fun SelectSizeCup(
    volume: Int,
    cupScale: Float,
    selectedBeansSize: BeansSize = BeansSize.LOW
) {
    val animatedScale by animateFloatAsState(
        targetValue = cupScale,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$volume ML",
            color = Color(0x99000000),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = urbanisation,
            letterSpacing = .25.sp,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 64.dp)
                .align(Alignment.TopStart)
        )

        Box(
            modifier = Modifier
                .padding(top = 49.dp, bottom = 48.dp)
                .wrapContentSize()
                .scale(animatedScale),
            contentAlignment = Alignment.Center
        ) {
            AnimatedBeansDisplays(
                isLowBeansSelected = selectedBeansSize == BeansSize.LOW,
                isMediumBeansSelected = selectedBeansSize == BeansSize.MEDIUM,
                isHighBeansSelected = selectedBeansSize == BeansSize.HIGH
            )

            Image(
                painter = painterResource(id = R.drawable.meduim_cup),
                contentDescription = "coffee cup",
                modifier = Modifier.size(199.4.dp, 244.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.thechance_ic),
                contentDescription = "cup icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(66.dp)
            )
        }
    }
}

@Composable
fun AnimatedBeansDisplays(
    isLowBeansSelected: Boolean,
    isMediumBeansSelected: Boolean,
    isHighBeansSelected: Boolean
) {
    Box(
        modifier = Modifier.height(230.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = isLowBeansSelected || isMediumBeansSelected || isHighBeansSelected,
            enter = slideInVertically(
                animationSpec = tween(1000, delayMillis = 0),
                initialOffsetY = { -it * 3 }
            ),
            exit = slideOutVertically(
                animationSpec = tween(1000, delayMillis = 400),
                targetOffsetY = { -it * 4 }
            )
        ) {
            Image(
                painter = painterResource(R.drawable.variant1),
                contentDescription = "beans",
                modifier = Modifier.size(100.dp)
            )
        }

        AnimatedVisibility(
            visible = isMediumBeansSelected || isHighBeansSelected,
            enter = slideInVertically(
                animationSpec = tween(1000, delayMillis = 300),
                initialOffsetY = { -it * 3 }
            ),
            exit = slideOutVertically(
                animationSpec = tween(1000, delayMillis = 200),
                targetOffsetY = { -it * 4 }
            )
        ) {
            Image(
                painter = painterResource(R.drawable.variant1),
                contentDescription = "beans",
                modifier = Modifier.size(100.dp)
            )
        }

        AnimatedVisibility(
            visible = isHighBeansSelected,
            enter = slideInVertically(
                animationSpec = tween(1000, delayMillis = 100),
                initialOffsetY = { -it * 3 }
            ),
            exit = slideOutVertically(
                animationSpec = tween(1000),
                targetOffsetY = { -it * 4 }
            )
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    painter = painterResource(R.drawable.variant1),
                    contentDescription = "beans",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}