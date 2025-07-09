package com.menna.caffeine.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.menna.caffeine.ui.screen.drink_selection.ChooseDrinkScreen
import com.menna.caffeine.ui.screen.preparing_coffee.PreparingCoffeeContent
import com.menna.caffeine.ui.screen.preparing_coffee.PreparingCoffeeScreen
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeScreen
import com.menna.caffeine.ui.screen.start_screen.StartScreen

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
            val drinkName = backStackEntry.arguments?.getString("drinkName") ?: ""
            SelectSizeScreen(
                navController = navController,
                drinkName = drinkName
            )
        }
        composable(
            route = AppDestination.FOURTH,
            arguments = listOf(
                navArgument("volume") { type = NavType.IntType },
                navArgument("scale") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val volume = backStackEntry.arguments?.getInt("volume") ?: 0
            val scale = backStackEntry.arguments?.getString("scale")?.toFloatOrNull() ?: 1f
            PreparingCoffeeScreen(navController, volume, scale)
        }
        composable(AppDestination.FIFTH) {
            Text(text = "Coffee is ready!", modifier = Modifier.fillMaxSize())
        }

    }
}