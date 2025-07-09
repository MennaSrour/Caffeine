package com.menna.caffeine.ui.screen.snack_selection

import androidx.lifecycle.ViewModel
import com.menna.caffeine.data_source.DummyDataSource
import com.menna.caffeine.ui.component.Snack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class SnackSelectionViewModel(
    private val dataSource: DummyDataSource
) : ViewModel() {

    private val _snacks = MutableStateFlow<List<Snack>>(emptyList())
    val snacks: StateFlow<List<Snack>> = _snacks.asStateFlow()

    private val _selectedSnack = MutableStateFlow<Snack?>(null)
    val selectedSnack: StateFlow<Snack?> = _selectedSnack.asStateFlow()

    init {
        loadSnacks()
    }

    private fun loadSnacks() {
        _snacks.value = dataSource.getSnackList()
    }

    fun selectSnack(snack: Snack) {
        _selectedSnack.value = snack
    }
}
