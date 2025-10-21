package com.panamericana.app.ui.screens.restaurant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.panamericana.app.model.MenuItem
import com.panamericana.app.ui.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: RestaurantViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val restaurant = uiState.selectedRestaurant ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurant.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            // Barra inferior que muestra el total y el botón para ver el pedido
            MenuBottomBar(
                itemCount = uiState.cart.values.sum(),
                totalCost = uiState.totalCost,
                onViewOrderClick = { navController.navigate("order_summary") }
            )
        }
    ) { paddingValues -> // El parámetro se llama 'paddingValues' por convención
        LazyColumn(
            // CORRECCIÓN: Se usa el padding que nos da el Scaffold
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(bottom = 100.dp) // Espacio extra al final
        ) {
            // Sección de Platos
            item {
                MenuHeader("Platos")
            }
            items(restaurant.menu.dishes) { dish ->
                MenuItemRow(
                    item = dish,
                    quantity = uiState.cart[dish] ?: 0,
                    onAdd = { viewModel.addItemToCart(dish) },
                    onRemove = { viewModel.removeItemFromCart(dish) }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }

            // Sección de Bebidas
            item {
                MenuHeader("Bebidas")
            }
            items(restaurant.menu.drinks) { drink ->
                MenuItemRow(
                    item = drink,
                    quantity = uiState.cart[drink] ?: 0,
                    onAdd = { viewModel.addItemToCart(drink) },
                    onRemove = { viewModel.removeItemFromCart(drink) }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

// --- COMPOSABLES DE AYUDA PARA LA PANTALLA DE MENÚ ---

@Composable
private fun MenuHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun MenuItemRow(
    item: MenuItem,
    quantity: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("S/ ${"%.2f".format(item.price)}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Contador de cantidad
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (quantity > 0) {
                IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Remove, "Quitar")
                }
                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            FilledIconButton(onClick = onAdd, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Default.Add, "Añadir")
            }
        }
    }
}

@Composable
private fun MenuBottomBar(itemCount: Int, totalCost: Float, onViewOrderClick: () -> Unit) {
    if (itemCount > 0) { // La barra solo aparece si hay algo en el carrito
        Surface(shadowElevation = 8.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$itemCount items • S/ ${"%.2f".format(totalCost)}", fontWeight = FontWeight.Bold)
                Button(onClick = onViewOrderClick) {
                    Text("Ver Pedido")
                }
            }
        }
    }
}