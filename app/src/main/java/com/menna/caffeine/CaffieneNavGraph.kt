package com.menna.caffeine

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.menna.caffeine.screen.AppDestination
import com.menna.caffeine.screen.ChooseDrinkScreen
import com.menna.caffeine.screen.SelectSizeScreen
import com.menna.caffeine.screen.StartScreen

@Composable
fun CaffieneNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppDestination.FIRST) {
        composable(AppDestination.FIRST) { StartScreen(navController) }
        composable(AppDestination.SECOND) { ChooseDrinkScreen(navController) }
        composable(
            route = AppDestination.THIRD + "/{drinkName}",
            arguments = listOf(
                navArgument("drinkName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // استخراج drinkName من arguments
            val drinkName = backStackEntry.arguments?.getString("drinkName") ?: ""
            SelectSizeScreen(
                navController = navController,
                drinkName = drinkName
            )
        }
    }
}