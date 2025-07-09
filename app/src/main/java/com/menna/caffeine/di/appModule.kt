package com.menna.caffeine.di

import androidx.lifecycle.SavedStateHandle
import com.menna.caffeine.data_source.DummyDataSource
import com.menna.caffeine.data_source.DummyDataSourceImpl
import com.menna.caffeine.ui.screen.ThankYouViewModel
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeViewModel
import com.menna.caffeine.ui.screen.snack_selection.SnackSelectionViewModel
import com.menna.caffeine.ui.screen.take_away.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DummyDataSource> { DummyDataSourceImpl() }

    viewModel { SelectSizeViewModel(get()) }
    viewModel { ThankYouViewModel(get()) }
    viewModel { SnackSelectionViewModel(get()) }
    viewModel {
        OrderViewModel(get())
    }
}
