package com.panamericana.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.panamericana.app.ui.screens.*
import com.panamericana.app.ui.viewmodel.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }
        composable(
            route = "list/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            val items = homeViewModel.getItemsByCategory(categoryName)
            ListScreen(navController = navController, items = items, categoryName = categoryName)
        }
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("itemId") ?: 0
            val item = homeViewModel.getItemById(id)
            if (item != null) {
                // CAMBIO CLAVE: Se elimina 'as Place' y se pasa el 'item' directamente.
                // El parámetro ahora se llama 'item', no 'place'.
                DetailScreen(navController = navController, item = item)
            }
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
    }
}