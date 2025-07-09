package com.menna.caffeine.ui.screen

import androidx.lifecycle.ViewModel
import com.menna.caffeine.data_source.DummyDataSource
import com.menna.caffeine.ui.component.Snack

class ThankYouViewModel(
    private val dummyDataSource: DummyDataSource
) : ViewModel() {

    fun getSelectedSnackById(id: Int): Snack? {
        return dummyDataSource.getSnackList().find { it.id == id }
    }
}
