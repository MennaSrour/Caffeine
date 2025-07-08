package com.menna.caffeine.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SelectSizeViewModel : ViewModel() {

    var selectedSize by mutableStateOf(CupSize.M)
        private set

    var selectedSeedsSize by mutableStateOf(SeedsSize.LOW)
        private set

    val cupVolume: Int
        get() = when (selectedSize) {
            CupSize.S -> 150
            CupSize.M -> 200
            CupSize.L -> 250
        }

    val cupScale: Float
        get() = when (selectedSize) {
            CupSize.S -> 0.8f
            CupSize.M -> 1.0f
            CupSize.L -> 1.2f
        }

    val logoSize: Int
        get() = when (selectedSize) {
            CupSize.S -> 50
            CupSize.M -> 66
            CupSize.L -> 80
        }

    fun updateSelectedSize(size: CupSize) {
        selectedSize = size
    }

    fun updateSeedsSize(size: SeedsSize) {
        selectedSeedsSize = size
    }
}