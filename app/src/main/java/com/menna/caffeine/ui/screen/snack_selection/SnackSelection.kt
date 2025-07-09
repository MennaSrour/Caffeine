package com.menna.caffeine.ui.screen.snack_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.menna.caffeine.ui.component.Snack
import com.menna.caffeine.ui.component.SnackSlider
import com.menna.caffeine.ui.theme.urbanisation
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.ui.component.IconCircleButton
import com.menna.caffeine.ui.navigation.AppDestination


@Composable
fun SnackSelectionScreen(
    navController: NavController
) {
    val viewModel: SnackSelectionViewModel = koinViewModel()

    val snacks by viewModel.snacks.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f,
        pageCount = { snacks.size }
    )

    SnackSelection(
        snacks = snacks,
        pagerState = pagerState,
        onItemClick = { selectedSnack ->
            viewModel.selectSnack(selectedSnack)
            navController.navigate(AppDestination.sevenWithArgs(selectedSnack.id))
        },
        onCancelClick = { navController.popBackStack(AppDestination.FIRST, false) }
    )
}

@Composable
private fun SnackSelection(
    snacks: List<Snack>,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onItemClick: (Snack) -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().systemBarsPadding()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconCircleButton(
                onclickButton = onCancelClick,
                icon = R.drawable.cancel
            )
        }
        Text(
            text = "Take your snack",
            color = Color(0xDE1F1F1F),
            fontFamily = urbanisation,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = 0.25.sp
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SnackSlider(
                pagerState = pagerState,
                snacks = snacks,
                onItemClick = onItemClick
            )
        }
    }
}
