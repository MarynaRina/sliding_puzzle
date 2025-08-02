package com.example.sliding_puzzle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sliding_puzzle.ui.screen.GameScreen
import com.example.sliding_puzzle.ui.screen.ImageSelectScreen
import com.example.sliding_puzzle.ui.screen.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("select") {
            ImageSelectScreen(navController = navController)
        }
        composable(
            route = "game/{imageResId}/{difficulty}",
            arguments = listOf(
                navArgument("imageResId") { type = NavType.IntType },
                navArgument("difficulty") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val imageResId = backStackEntry.arguments?.getInt("imageResId")
            val difficulty = backStackEntry.arguments?.getInt("difficulty") ?: 4
            GameScreen(imageResId = imageResId, difficulty = difficulty)
        }
    }
}
