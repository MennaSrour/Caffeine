package com.menna.caffeine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.component.GhostImage
import com.menna.caffeine.component.Header
import com.menna.caffeine.component.HocusText
import com.menna.caffeine.component.TextIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun StartScreen(
    navController: NavController,
) {
    StartContent { navController.navigate(AppDestination.SECOND) }
}

@Composable
fun StartContent(
    onClickNext: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        HocusText()
        GhostImage()
        TextIconButton(
            onClickNext,
            "bring my coffee",
            R.drawable.coffee_icon,
            Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview()
@Composable
fun Preview() {
    StartScreen(
        navController = TODO(),
    )
}
