package com.menna.caffeine.di

import com.menna.caffeine.data_source.SelectSizeDataSource
import com.menna.caffeine.data_source.SelectSizeDataSourceImpl
import com.menna.caffeine.ui.screen.drink_selection.ChooseDrinkViewModel
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeViewModel
import com.menna.caffeine.ui.screen.start_screen.StartViewModel
import org.koin.dsl.module

val appModule = module {
    single<SelectSizeDataSource> { SelectSizeDataSourceImpl() }
    single { StartViewModel() }
    single { ChooseDrinkViewModel() }
    single { SelectSizeViewModel(get()) }
}