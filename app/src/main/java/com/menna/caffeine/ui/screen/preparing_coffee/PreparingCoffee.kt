package com.menna.caffeine.ui.screen.preparing_coffee

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menna.caffeine.ui.component.LoadingLine
import com.menna.caffeine.ui.component.PreparingText
import com.menna.caffeine.ui.component.SelectSizeCup
import com.menna.caffeine.ui.navigation.AppDestination
import kotlinx.coroutines.delay

@Composable
fun PreparingCoffeeScreen(
    navController: NavController,
    volume: Int,
    scale: Float
) {
    LaunchedEffect(Unit) {
        delay(10000)
        navController.navigate(AppDestination.FIFTH) {
            popUpTo(AppDestination.FOURTH) { inclusive = true }
        }
    }
    PreparingCoffeeContent(
        volume,
        scale
    )
}

@Composable
fun PreparingCoffeeContent(
    volume: Int,
    scale: Float
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding()

    ) {
        SelectSizeCup(
            volume = volume,
            cupScale = scale,
        )

        LoadingLine(
            modifier = Modifier
                .padding(top = 101.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        PreparingText(
            Modifier
                .align(Alignment.End).padding(bottom = 50.dp)
        )
    }
}
