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
        // NUEVA RUTA: Recibe el nombre de la categoría para mostrar una lista
        composable(
            route = "list/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            requireNotNull(categoryName)
            val places = homeViewModel.getPlacesByCategory(categoryName)
            ListScreen(navController = navController, places = places, categoryName = categoryName)
        }
        composable(
            route = "detail/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("placeId")
            requireNotNull(id)
            val place = homeViewModel.getPlaceById(id)
            if (place != null) {
                DetailScreen(navController = navController, place = place)
            }
        }
    }
}