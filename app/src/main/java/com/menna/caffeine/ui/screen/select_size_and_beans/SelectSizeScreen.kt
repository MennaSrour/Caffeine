package com.menna.caffeine.ui.screen.select_size_and_beans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.ui.component.BeansSizeButtons
import com.menna.caffeine.ui.component.CupSizeButtons
import com.menna.caffeine.ui.component.SelectSizeCup
import com.menna.caffeine.ui.component.SizeSelectorHeader
import com.menna.caffeine.ui.component.TextIconButton
import com.menna.caffeine.ui.navigation.AppDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectSizeScreen(
    navController: NavController,
    drinkName: String,
    viewModel: SelectSizeViewModel = koinViewModel()
) {
    SelectSizeContent(
        uiState = viewModel.uiState,
        onSizeSelected = { viewModel.updateSelectedSize(it) },
        onSeedsSelected = { viewModel.updateSeedsSize(it) },
        onClickNext = {
            val volume = viewModel.uiState.cupVolume
            val scale = viewModel.uiState.cupScale
            navController.navigate(AppDestination.fourthWithArgs(volume, scale.toString()))
        },
        onClickPrevious = { navController.popBackStack() },
        drinkName = drinkName
    )
}

@Composable
fun SelectSizeContent(
    uiState: SelectSizeState,
    onSizeSelected: (SelectSizeState.CupSize) -> Unit,
    onSeedsSelected: (SelectSizeState.BeansSize) -> Unit,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    drinkName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(color = Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SizeSelectorHeader(
            onClickPrevious,
            drinkName
        )

        SelectSizeCup(
            volume = uiState.cupVolume,
            cupScale = uiState.cupScale,
            selectedBeansSize = uiState.selectedBeansSize
        )

        CupSizeButtons(
            selectedSize = uiState.selectedSize,
            onSizeSelected = onSizeSelected,
        )

        BeansSizeButtons(
            selectedSize = uiState.selectedBeansSize,
            onSizeSelected = onSeedsSelected,
            modifier = Modifier.padding(top = 16.dp)
        )

        TextIconButton(
            onClickNext,
            "Continue",
            R.drawable.arrow_right,
            Modifier.padding(top = 24.dp)
        )
    }
}