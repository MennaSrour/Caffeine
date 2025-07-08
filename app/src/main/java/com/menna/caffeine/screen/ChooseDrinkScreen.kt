package com.menna.caffeine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.component.ChooseDrinkPager
import com.menna.caffeine.component.Header
import com.menna.caffeine.component.TextIconButton
import com.menna.caffeine.component.WelcomeText

@Composable
fun ChooseDrinkScreen(
    navController: NavController,
) {
    var selectedDrink by remember { mutableStateOf("Black") }

    ChooseDrinkContent(
        onDrinkSelected = { drinkName ->
            selectedDrink = drinkName
        },
        onclickNext = {
            navController.navigate("${AppDestination.THIRD}/$selectedDrink")
        }
    )
}

@Composable
fun ChooseDrinkContent(
    onDrinkSelected: (String) -> Unit,
    onclickNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
    ) {
        Header()
        WelcomeText("Menna")
        ChooseDrinkPager(
            onDrinkSelected = onDrinkSelected
        )
        TextIconButton(
            onclickNext,
            "Continue",
            R.drawable.arrow_right,
            Modifier.padding(bottom = 50.dp).align(Alignment.CenterHorizontally)
        )
    }
}
@Preview
@Composable
fun ScreenPreview() {
    ChooseDrinkScreen(
        navController = TODO()
    )
}