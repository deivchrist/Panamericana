package com.panamericana.app.ui.screens.restaurant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.panamericana.app.model.MenuItem
import com.panamericana.app.ui.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSummaryScreen(navController: NavController, viewModel: RestaurantViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val restaurant = uiState.selectedRestaurant ?: return
    val cartItems = uiState.cart.entries.toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumen de tu Pedido") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total a Pagar:", style = MaterialTheme.typography.titleMedium)
                        Text("S/ ${"%.2f".format(uiState.totalCost)}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        // CORRECCIÓN: El botón ahora navega a la pantalla de pago
                        onClick = { navController.navigate("restaurant_payment") },
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("Continuar al Pago")
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text("Tu pedido en:", style = MaterialTheme.typography.titleMedium)
                Text(restaurant.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
            }

            items(cartItems) { (menuItem, quantity) ->
                CartItemRow(item = menuItem, quantity = quantity)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun CartItemRow(item: MenuItem, quantity: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${quantity}x",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            Text(text = "S/ ${"%.2f".format(item.price)} c/u", style = MaterialTheme.typography.bodySmall)
        }
        Text(
            text = "S/ ${"%.2f".format(item.price * quantity)}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}