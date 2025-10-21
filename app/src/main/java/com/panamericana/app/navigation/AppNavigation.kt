package com.panamericana.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.panamericana.app.data.UserPreferencesRepository
import com.panamericana.app.ui.screens.*
import com.panamericana.app.ui.screens.booking.ConfirmationScreen
import com.panamericana.app.ui.screens.booking.PaymentScreen
import com.panamericana.app.ui.screens.booking.SeatSelectionScreen
import com.panamericana.app.ui.screens.restaurant.MenuScreen
import com.panamericana.app.ui.screens.restaurant.OrderSummaryScreen
import com.panamericana.app.ui.screens.restaurant.RestaurantConfirmationScreen
import com.panamericana.app.ui.viewmodel.*
import com.panamericana.app.ui.screens.restaurant.RestaurantPaymentScreen

@Composable
fun AppHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferencesRepository = remember { UserPreferencesRepository(context) }

    val isLoggedIn by userPreferencesRepository.isLoggedIn.collectAsState(initial = false)
    val startDestination = if (isLoggedIn) "main_app" else "auth"

    NavHost(navController = navController, startDestination = startDestination) {
        authNavGraph(navController)
        mainAppNavGraph(navController)
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") { LoginScreen(navController = navController, authViewModel = viewModel()) }
        composable("register") { RegisterScreen(navController = navController, authViewModel = viewModel()) }
    }
}

fun NavGraphBuilder.mainAppNavGraph(navController: NavHostController) {
    navigation(startDestination = "home", route = "main_app") {

        composable("home") {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }
        composable(
            route = "list/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) {
            val homeViewModel: HomeViewModel = viewModel()
            val categoryName = it.arguments?.getString("categoryName") ?: ""
            val items = homeViewModel.getItemsByCategory(categoryName)
            ListScreen(navController = navController, items = items, categoryName = categoryName)
        }
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Se obtienen ambos ViewModels compartidos para pasarlos al detalle
            val bookingViewModel = backStackEntry.sharedViewModel<BookingViewModel>(navController)
            val restaurantViewModel = backStackEntry.sharedViewModel<RestaurantViewModel>(navController)
            val homeViewModel: HomeViewModel = viewModel()
            val id = backStackEntry.arguments?.getInt("itemId") ?: 0
            val item = homeViewModel.getItemById(id)
            if (item != null) {
                DetailScreen(navController, item, bookingViewModel, restaurantViewModel)
            }
        }
        composable("profile") {
            val profileViewModel: ProfileViewModel = viewModel()
            ProfileScreen(navController = navController, profileViewModel = profileViewModel)
        }
        composable("edit_profile") {
            val profileViewModel: ProfileViewModel = viewModel()
            EditProfileScreen(navController = navController, profileViewModel = profileViewModel)
        }

        // --- RUTAS PARA EL FLUJO DE RESERVA DE EVENTOS ---
        composable("seat_selection") { backStackEntry ->
            val bookingViewModel = backStackEntry.sharedViewModel<BookingViewModel>(navController)
            SeatSelectionScreen(navController, bookingViewModel)
        }
        composable("payment") { backStackEntry ->
            val bookingViewModel = backStackEntry.sharedViewModel<BookingViewModel>(navController)
            PaymentScreen(navController, bookingViewModel)
        }
        composable("confirmation") {
            ConfirmationScreen(navController)
        }

        // --- NUEVAS RUTAS PARA EL FLUJO DE RESERVA DE RESTAURANTES ---
        composable("menu") { backStackEntry ->
            val restaurantViewModel = backStackEntry.sharedViewModel<RestaurantViewModel>(navController)
            MenuScreen(navController, restaurantViewModel)
        }
        composable("order_summary") { backStackEntry ->
            val restaurantViewModel = backStackEntry.sharedViewModel<RestaurantViewModel>(navController)
            OrderSummaryScreen(navController, restaurantViewModel)
        }
        // NUEVA RUTA
        composable("restaurant_payment") { backStackEntry ->
            val restaurantViewModel = backStackEntry.sharedViewModel<RestaurantViewModel>(navController)
            RestaurantPaymentScreen(navController, restaurantViewModel)
        }
        composable("restaurant_confirmation") { backStackEntry ->
            val restaurantViewModel = backStackEntry.sharedViewModel<RestaurantViewModel>(navController)
            RestaurantConfirmationScreen(navController, restaurantViewModel)
        }
    }
}

// Función de ayuda para obtener un ViewModel compartido a nivel del grafo de navegación
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}