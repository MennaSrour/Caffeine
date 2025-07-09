package com.menna.caffeine.ui.screen.take_away
import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.menna.caffeine.R
import com.menna.caffeine.ui.navigation.AppDestination
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OrderReadyScreen(
    navController: NavController,
    coffeeId: Int,
    viewModel:OrderViewModel = koinViewModel(),

    ) {


    val uiState by viewModel.uiState.collectAsState()
    OrderReadyScreenContent(
        uiState = uiState,
        onTakeAwayToggled = viewModel::onTakeAwayToggled,
        onTakeSnackClicked = {
            navController.navigate(AppDestination.SIX)
        },
        onCloseClicked = {
            navController.popBackStack(AppDestination.FIRST,false)

        }
    )
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun OrderReadyScreenContent(
    uiState: OrderUiState,
    onTakeAwayToggled: () -> Unit,
    onTakeSnackClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {

    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        isContentVisible = true
    }

    val lidOffsetY by animateDpAsState(
        targetValue = if (isContentVisible) -(150).dp else (-600).dp,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 200,
            easing = EaseOutCubic
        ),
        label = "lidOffsetAnimation"
    )

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if (isLandscape) {
            LandscapeLayout(
                uiState = uiState,
                isContentVisible = isContentVisible,
                lidOffsetY = lidOffsetY,
                onCloseClicked = onCloseClicked,
                onTakeAwayToggled = onTakeAwayToggled,
                onTakeSnackClicked = onTakeSnackClicked
            )
        } else {
            PortraitLayout(
                uiState = uiState,
                isContentVisible = isContentVisible,
                lidOffsetY = lidOffsetY,
                onCloseClicked = onCloseClicked,
                onTakeAwayToggled = onTakeAwayToggled,
                onTakeSnackClicked = onTakeSnackClicked
            )
        }
    }
}

@Composable
private fun LandscapeLayout(
    uiState: OrderUiState,
    isContentVisible: Boolean,
    lidOffsetY: Dp,
    onCloseClicked: () -> Unit,
    onTakeAwayToggled: () -> Unit,
    onTakeSnackClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CoffeeCup(
                coffeeImages = uiState.selectedCoffee!!.images,
                lidOffsetY = lidOffsetY,
                modifier = Modifier
                    .fillMaxHeight(0.8f) // Adjusted to fit height
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                StatusIconAndText(isVisible = isContentVisible)
                Spacer(modifier = Modifier.weight(1f))
                BottomSection(
                    isVisible = isContentVisible,
                    isTakeAway = uiState.isTakeAway,
                    topImage = uiState.selectedCoffee!!.images.top,
                    onTakeAwayToggled = onTakeAwayToggled,
                    onTakeSnackClicked = onTakeSnackClicked
                )
            }
        }
        CircularCloseButton(
            onClick = onCloseClicked,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )
    }
}

@Composable
private fun StatusIconAndText(isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 600, delayMillis = 200))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .drawBehind {
                        drawIntoCanvas {
                            val paint = Paint()
                            val frameworkPaint = paint.asFrameworkPaint()
                            val color = Color(0x80B94B23)
                            val blurRadius = 16.dp.toPx()

                            if (blurRadius > 0) {
                                frameworkPaint.maskFilter = (BlurMaskFilter(
                                    blurRadius,
                                    BlurMaskFilter.Blur.NORMAL
                                ))
                            }
                            frameworkPaint.color = color.toArgb()

                            it.drawCircle(
                                center = center,
                                radius = size.width / 2,
                                paint = paint
                            )
                        }
                    }
                    .clip(CircleShape)
                    .background(Color(0xFF7C351B)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Order Ready",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = "Your coffee is ready.\nEnjoy",
                fontSize = 24.sp, // Slightly smaller for landscape
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
private fun PortraitLayout(
    uiState: OrderUiState,
    isContentVisible: Boolean,
    lidOffsetY: Dp,
    onCloseClicked: () -> Unit,
    onTakeAwayToggled: () -> Unit,
    onTakeSnackClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isContentVisible,
            enter = slideInVertically(animationSpec = tween(800, easing = EaseOutCubic)) { -it }
        ) {
            TopSection(onCloseClicked = onCloseClicked)
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CoffeeCup(
                coffeeImages = uiState.selectedCoffee!!.images,
                lidOffsetY = lidOffsetY,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .aspectRatio(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomSection(
            isVisible = isContentVisible,
            isTakeAway = uiState.isTakeAway,
            topImage = uiState.selectedCoffee!!.images.top,
            onTakeAwayToggled = onTakeAwayToggled,
            onTakeSnackClicked = onTakeSnackClicked
        )
    }
}

@Composable
private fun TopSection(
    onCloseClicked: () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CircularCloseButton(
                onClick = onCloseClicked,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .size(64.dp)
                .drawBehind{
                    drawIntoCanvas {
                        val paint = Paint()
                        val frameworkPaint = paint.asFrameworkPaint()
                        val color = Color(0x80B94B23)
                        val blurRadius = 16.dp.toPx()

                        if (blurRadius > 0) {
                            frameworkPaint.maskFilter = (BlurMaskFilter(
                                blurRadius,
                                BlurMaskFilter.Blur.NORMAL
                            ))
                        }
                        frameworkPaint.color = color.toArgb()

                        it.drawCircle(
                            center = center,
                            radius = size.width / 2,
                            paint = paint
                        )
                    }
                }
                .clip(CircleShape)
                .background(Color(0xFF7C351B)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Order Ready",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            text = "Your coffee is ready.\nEnjoy",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
private fun CircularCloseButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = 0.15f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.DarkGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun CoffeeCup(
    coffeeImages: CoffeeImages,
    modifier: Modifier = Modifier,
    lidOffsetY: Dp) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.75f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        // The Base Cup
        Image(
            painter = painterResource(id = coffeeImages.cupBody),
            contentDescription = "Coffee cup",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.thechance),
            contentDescription = "The Chance Coffee Logo",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = coffeeImages.cover),
            contentDescription = "Coffee Lid",
            modifier = Modifier
                .fillMaxSize()
                .offset(y = lidOffsetY),
        )
    }
}

@Composable
private fun BottomSection(
    isVisible: Boolean,
    isTakeAway: Boolean,
    topImage: Int,
    onTakeAwayToggled: () -> Unit,
    onTakeSnackClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 600, delayMillis = 400))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoffeeToggleButton(
                    isToggled = isTakeAway,
                    topImage = topImage,
                    onToggle = onTakeAwayToggled
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Take Away",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                animationSpec = tween(durationMillis = 800, easing = EaseOutCubic)
            ) { it }
        ) {
            Button(
                onClick = onTakeSnackClicked,
                modifier = Modifier
                    .width(180.dp)
                    .height(56.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Take snack", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun CoffeeToggleButton(
    topImage: Int,
    isToggled: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val trackColor by animateColorAsState(
        targetValue = if (isToggled) Color(0xFF8B5A2B) else Color(0xFFFBEAE0),
        animationSpec = tween(300),
        label = "trackColor"
    )

    val horizontalBias by animateFloatAsState(
        targetValue = if (isToggled) -1f else 1f,
        animationSpec = tween(300),
        label = "horizontalBias"
    )

    Box(
        modifier = modifier
            .width(120.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(50))
            .background(trackColor)
            .clickable(
                onClick = onToggle,
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Disable ripple effect
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {

        Text(
            text = "ON",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp)
        )

        Text(
            text = "OFF",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )


        Box(
            modifier = Modifier
                .fillMaxHeight()
                .align(BiasAlignment(horizontalBias = horizontalBias, verticalBias = 0f))
        ) {
            Image(
                painter = painterResource(id = topImage),
                contentDescription = "Toggle Thumb",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
    }
}
