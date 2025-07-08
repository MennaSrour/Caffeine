package com.menna.caffeine.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.component.SizeSelectorHeader
import com.menna.caffeine.component.TextIconButton
import com.menna.caffeine.component.utils.dropShadow
import com.menna.caffeine.ui.theme.urabanist
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

enum class SeedsSize {
    LOW, MEDIUM, HIGH
}

@Composable
fun SelectSizeScreen(
    navController: NavController,
    drinkName: String,
    viewModel: SelectSizeViewModel = koinViewModel()
) {
    SelectSizeContent(
        selectedSize = viewModel.selectedSize,
        cupVolume = viewModel.cupVolume,
        cupScale = viewModel.cupScale,
        logoSize = viewModel.logoSize,
        onSizeSelected = { viewModel.updateSelectedSize(it) },
        onClickNext = { navController.navigate(AppDestination.FOURTH) },
        onClickPrevious = { navController.popBackStack() },
        drinkName = drinkName,
        selectedSeedsSize = viewModel.selectedSeedsSize,
        onSeedsSelected = { viewModel.updateSeedsSize(it) }
    )
}

@Composable
fun SelectSizeContent(
    selectedSize: CupSize,
    cupVolume: Int,
    cupScale: Float,
    logoSize: Int,
    onSizeSelected: (CupSize) -> Unit,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    drinkName: String,
    selectedSeedsSize: SeedsSize,
    onSeedsSelected: (SeedsSize) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SizeSelectorHeader(
            onClickPrevious,
            drinkName
        )

        Cup(
            volume = cupVolume,
            cupScale = cupScale,
            logoSize = logoSize,
            selectedSeedsSize = selectedSeedsSize
        )

        CircleButton(
            selectedSize = selectedSize,
            onSizeSelected = onSizeSelected,
        )

        SeedsSizeButtons(
            selectedSize = selectedSeedsSize,
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

enum class CupSize {
    S, M, L
}

@Composable
fun CircleButton(
    selectedSize: CupSize,
    onSizeSelected: (CupSize) -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(50.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CupSize.entries.forEach { size ->
            val isSelected = selectedSize == size

            val targetColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                animationSpec = tween(durationMillis = 200)
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xDEFFFFFF) else Color(0x991F1F1F),
                animationSpec = tween(durationMillis = 200)
            )

            Button(
                onClick = { onSizeSelected(size) },
                modifier = Modifier
                    .width(40.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                        alpha = if (isSelected) 0.5f else 0.3f,
                        blur = 4.dp,
                        offsetX = 0.dp,
                        offsetY = 2.dp
                    ),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = targetColor
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = size.name.first().toString(),
                    color = textColor,
                    fontFamily = urabanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = .25.sp,
                )
            }
        }
    }
}

@Composable
fun Cup(
    volume: Int,
    cupScale: Float,
    logoSize: Int,
    selectedSeedsSize: SeedsSize
) {
    val animatedScale by animateFloatAsState(
        targetValue = cupScale,
        animationSpec = tween(durationMillis = 300)
    )

    val seedsAlpha by animateFloatAsState(
        targetValue = when (selectedSeedsSize) {
            SeedsSize.LOW -> 0.4f
            SeedsSize.MEDIUM -> 0.7f
            SeedsSize.HIGH -> 1.0f
        },
        animationSpec = tween(durationMillis = 300)
    )

    var initialDrop by remember { mutableStateOf(true) }

    val seedsOffsetY by animateDpAsState(
        targetValue = if (initialDrop) {
            -400.dp
        } else {
            when (selectedSeedsSize) {
                SeedsSize.LOW -> -100.dp
                SeedsSize.MEDIUM -> 0.dp
                SeedsSize.HIGH -> 40.dp
            }
        },
        animationSpec = tween(durationMillis = 700, easing = EaseOut)
    )

    LaunchedEffect(Unit) {
        initialDrop = true
        delay(700)
        initialDrop = false
    }

    val seedsSize by animateDpAsState(
        targetValue = when (selectedSeedsSize) {
            SeedsSize.LOW -> 80.dp
            SeedsSize.MEDIUM -> 100.dp
            SeedsSize.HIGH -> 120.dp
        },
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
            fontFamily = urabanist,
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
                .scale(animatedScale)
        ) {
            Image(
                painter = painterResource(R.drawable.variant1),
                contentDescription = "Coffee Seeds",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = seedsOffsetY)
                    .size(seedsSize)
                    .alpha(seedsAlpha)
            )

            Image(
                painter = painterResource(id = R.drawable.meduim_cup),
                contentDescription = "coffee cup",
                modifier = Modifier
                    .size(199.4.dp, 244.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.thechance_ic),
                contentDescription = "cup icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(logoSize.dp)
            )
        }
    }
}


@Composable
fun SeedsSizeButtons(
    selectedSize: SeedsSize,
    onSizeSelected: (SeedsSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .background(
                Color(0xFFF5F5F5),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SeedsSize.entries.forEach { size ->
            val isSelected = selectedSize == size

            val targetColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                animationSpec = tween(durationMillis = 200)
            )

            val iconTint by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color(0x991F1F1F),
                animationSpec = tween(durationMillis = 200)
            )

            Button(
                onClick = { onSizeSelected(size) },
                modifier = Modifier
                    .size(40.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFF7C351B) else Color(0xFFF5F5F5),
                        alpha = if (isSelected) 0.5f else 0.3f,
                        blur = 4.dp,
                        offsetX = 0.dp,
                        offsetY = 2.dp
                    ),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = targetColor
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.elements),
                    contentDescription = "Seed Icon",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Preview(device = "id:pixel_8a", showBackground = true)
@Composable
fun SelectSizePreview() {
    SelectSizeContent(
        selectedSize = CupSize.M,
        cupVolume = 200,
        cupScale = 1.0f,
        logoSize = 66,
        onSizeSelected = { /* handle selection */ },
        onClickNext = { /* handle next */ },
        onClickPrevious = { /* handle back */ },
        drinkName = "Macchiato",
        selectedSeedsSize = SeedsSize.MEDIUM,
        onSeedsSelected = { /* handle seeds selection */ }
    )
}