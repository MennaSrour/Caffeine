package com.menna.caffeine.ui.screen.select_size_and_beans

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.menna.caffeine.data_source.DummyDataSource

class SelectSizeViewModel(
    private val dataSource: DummyDataSource
) : ViewModel() {

    var uiState by mutableStateOf(dataSource.getDefaultUiState())
        private set

    fun updateSelectedSize(size: SelectSizeState.CupSize) {
        uiState = uiState.copy(
            selectedSize = size,
            cupVolume = dataSource.getCupVolumeForSize(size),
            cupScale = dataSource.getCupScaleForSize(size)
        )
    }

    fun updateSeedsSize(size: SelectSizeState.BeansSize) {
        uiState = uiState.copy(
            selectedBeansSize = size
        )
    }

    fun getSeedsSizes(): List<SelectSizeState.BeansSize> {
        return dataSource.getSeedsSizes()
    }
}