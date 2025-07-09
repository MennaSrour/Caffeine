package com.menna.caffeine.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.menna.caffeine.R
import com.menna.caffeine.ui.theme.urbanisation
import kotlin.math.absoluteValue

data class DrinkItem(
    val imageRes: Int,
    val name: String
)

@Composable
fun ChooseDrinkPager(
    onDrinkSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val drinks = listOf(
        DrinkItem(R.drawable.espresso, "Espresso"),
        DrinkItem(R.drawable.macchiato, "Macchiato"),
        DrinkItem(R.drawable.latte, "Latte"),
        DrinkItem(R.drawable.black, "Black"),


        )

    val pagerState = rememberPagerState(
        initialPage = 3,
        pageCount = { drinks.size }
    )
    LaunchedEffect(pagerState.currentPage) {
        onDrinkSelected(drinks[pagerState.currentPage].name)
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .padding(top=56.dp)
            .fillMaxWidth().wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 80.dp),
        pageSpacing = (-2).dp
    ) { page ->
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                ).absoluteValue

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val scale = lerp(
                        start = 0.6f,
                        stop = 1.0f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale

                    alpha = lerp(
                        start = 0.6f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = drinks[page].imageRes),
                    contentDescription = "${drinks[page].name} coffee",
                )
            }

            Text(
                text = drinks[page].name,
                color = Color(0xFF1F1F1F),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = urbanisation,
                letterSpacing = .25.sp,
                lineHeight = 32.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseDrinkPagerPreview() {
    MaterialTheme {
        ChooseDrinkPager(
            onDrinkSelected = TODO(),
            modifier = TODO()
        )
    }
}