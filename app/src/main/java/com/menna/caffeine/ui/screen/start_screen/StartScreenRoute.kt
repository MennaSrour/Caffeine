package com.menna.caffeine.ui.screen.start_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.startScreenRoute(navController: NavController){
    composable(
        "startScreen",
    ){
        StartScreen(navController)
    }
}