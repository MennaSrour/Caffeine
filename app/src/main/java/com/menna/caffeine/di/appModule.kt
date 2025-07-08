package com.menna.caffeine.di

import com.menna.caffeine.screen.ChooseDrinkViewModel
import com.menna.caffeine.screen.SelectSizeViewModel
import com.menna.caffeine.screen.StartViewModel
import org.koin.dsl.module

val appModule = module {
    single { StartViewModel() }
    single { ChooseDrinkViewModel() }
    single { SelectSizeViewModel() }
}