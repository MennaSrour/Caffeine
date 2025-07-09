package com.menna.caffeine.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.ui.component.IconCircleButton
import com.menna.caffeine.ui.component.SelectedSnackCard
import com.menna.caffeine.ui.component.Snack
import com.menna.caffeine.ui.component.TextIconButton
import com.menna.caffeine.ui.component.ThankYouLine
import com.menna.caffeine.ui.navigation.AppDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun ThankYouScreen(
    navController: NavController,
    selectedItemId: Int,
    viewModel: ThankYouViewModel = koinViewModel()
) {
    val selectedSnack = viewModel.getSelectedSnackById(selectedItemId)

    ThankYouContent(
        onCancelClick = { navController.popBackStack(AppDestination.FIRST, false) },
        onClickNext = { navController.popBackStack(AppDestination.FIRST, false) },
        selectedSnack = selectedSnack
    )
}


@Composable
private fun ThankYouContent(
    onCancelClick: () -> Unit,
    onClickNext: () -> Unit,
    selectedSnack: Snack?
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconCircleButton(
                onclickButton = onCancelClick,
                icon = R.drawable.cancel
            )
        }
        ThankYouLine(modifier = Modifier.padding(bottom = 24.dp))
        selectedSnack?.let {
            SelectedSnackCard(image = it.imageRes)
        }
        Spacer(modifier = Modifier.weight(1f))
        TextIconButton(
            onClickNext = onClickNext,
            text = "Thank youuu",
            icon = R.drawable.arrow_right,
            modifier = Modifier.padding(bottom = 50.dp)
        )

    }
}
