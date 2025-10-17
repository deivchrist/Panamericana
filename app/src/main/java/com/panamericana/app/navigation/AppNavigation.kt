package com.panamericana.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.panamericana.app.ui.screens.DetailScreen
import com.panamericana.app.ui.screens.FavoritesScreen
import com.panamericana.app.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Pasamos el navController a la HomeScreen para que pueda navegar
            HomeScreen(navController = navController)
        }
        composable("detail") {
            DetailScreen()
        }
        composable("favorites") {
            FavoritesScreen()
        }
    }
}