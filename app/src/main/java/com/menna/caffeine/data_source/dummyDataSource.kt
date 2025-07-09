package com.menna.caffeine.data_source

import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState

interface SelectSizeDataSource {
    fun getDefaultUiState(): SelectSizeState
    fun getCupSizes(): List<SelectSizeState.CupSize>
    fun getSeedsSizes(): List<SelectSizeState.BeansSize>
    fun getCupVolumeForSize(size: SelectSizeState.CupSize): Int
    fun getCupScaleForSize(size: SelectSizeState.CupSize): Float
}

class SelectSizeDataSourceImpl : SelectSizeDataSource {

    override fun getDefaultUiState(): SelectSizeState {
        return SelectSizeState(
            selectedSize = SelectSizeState.CupSize.M,
            selectedBeansSize = SelectSizeState.BeansSize.LOW,
            cupVolume = 200,
            cupScale = 1.0f
        )
    }

    override fun getCupSizes(): List<SelectSizeState.CupSize> {
        return listOf(
            SelectSizeState.CupSize.S,
            SelectSizeState.CupSize.M,
            SelectSizeState.CupSize.L
        )
    }

    override fun getSeedsSizes(): List<SelectSizeState.BeansSize> {
        return listOf(
            SelectSizeState.BeansSize.LOW,
            SelectSizeState.BeansSize.MEDIUM,
            SelectSizeState.BeansSize.HIGH
        )
    }

    override fun getCupVolumeForSize(size: SelectSizeState.CupSize): Int {
        return size.getVolume()
    }

    override fun getCupScaleForSize(size: SelectSizeState.CupSize): Float {
        return size.getScale()
    }
}