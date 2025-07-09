package com.menna.caffeine.data_source

import com.menna.caffeine.R
import com.menna.caffeine.ui.component.Snack
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeState.CupSize
import com.menna.caffeine.ui.screen.take_away.Coffee
import com.menna.caffeine.ui.screen.take_away.CoffeeImages
import kotlin.collections.listOf

interface DummyDataSource {
    fun getDefaultUiState(): SelectSizeState
    fun getCupSizes(): List<SelectSizeState.CupSize>
    fun getSeedsSizes(): List<SelectSizeState.BeansSize>
    fun getCupVolumeForSize(size: SelectSizeState.CupSize): Int
    fun getCupScaleForSize(size: SelectSizeState.CupSize): Float
    fun getSnackList(): List<Snack>
    fun getCoffeeList(): List<Coffee>
}

class DummyDataSourceImpl : DummyDataSource {

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

    override fun getSnackList(): List<Snack> {
        return listOf(
            Snack(1, "Chocolate Muffin", R.drawable.chocolate),
            Snack(2, "CupCake", R.drawable.cupcake),
            Snack(3, "Cookies", R.drawable.cookies),
            Snack(4, "Cinnamon", R.drawable.cinnamon),
            Snack(5, "Croissant", R.drawable.croissant),
            Snack(6, "Oreo", R.drawable.oreo),
        )
    }

    override fun getCoffeeList(): List<Coffee> {
        return listOf(
            Coffee(
                id = 1,
                name = "Espresso",
                images = CoffeeImages(
                    cupBody = R.drawable.espresso_empty_cup,
                    cover = R.drawable.espresso_cover,
                    top = R.drawable.espresso_top
                ),
            ),
            Coffee(
                id = 2,
                name = "Latte",
                images = CoffeeImages(
                    cupBody = R.drawable.latte_empty_cup,
                    cover = R.drawable.lattee_cover,
                    top = R.drawable.lattee_top
                )
            )
        )
    }

}