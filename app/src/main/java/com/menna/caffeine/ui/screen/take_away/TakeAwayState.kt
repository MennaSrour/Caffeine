package com.menna.caffeine.ui.screen.take_away



data class OrderUiState(
    val selectedCoffee: Coffee? = null,
    val isTakeAway: Boolean = false
)
data class CoffeeImages(
    val cupBody: Int,
    val cover: Int,
    val top: Int
)
data class Coffee(
    val id: Int,
    val name: String,
    val images: CoffeeImages,
)