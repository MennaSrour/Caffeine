package com.menna.caffeine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.menna.caffeine.ui.screen.ThankYouScreen
import com.menna.caffeine.ui.screen.drink_selection.ChooseDrinkScreen
import com.menna.caffeine.ui.screen.preparing_coffee.PreparingCoffeeScreen
import com.menna.caffeine.ui.screen.select_size_and_beans.SelectSizeScreen
import com.menna.caffeine.ui.screen.snack_selection.SnackSelectionScreen
import com.menna.caffeine.ui.screen.start_screen.StartScreen
import com.menna.caffeine.ui.screen.take_away.OrderReadyScreen

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
            OrderReadyScreen(
                navController,
                coffeeId = 2,
            )
        }
        composable(AppDestination.SIX) {
            SnackSelectionScreen(
                navController
            )
        }
        composable(AppDestination.SEVEN) { backStackEntry ->
            val snackId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ThankYouScreen(
                selectedItemId = snackId,
                navController =navController,
            )
        }

    }
}