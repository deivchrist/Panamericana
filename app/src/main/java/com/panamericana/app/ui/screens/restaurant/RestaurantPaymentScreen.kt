package com.panamericana.app.ui.screens.restaurant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.panamericana.app.ui.viewmodel.OrderStatus
import com.panamericana.app.ui.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantPaymentScreen(navController: NavController, viewModel: RestaurantViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // Este efecto observará el estado de la orden. Si es exitoso, navega a la confirmación.
    LaunchedEffect(uiState.orderStatus) {
        if (uiState.orderStatus == OrderStatus.SUCCESS) {
            navController.navigate("restaurant_confirmation") {
                popUpTo("main_app") // Limpia el historial de reserva
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Pago") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Estás pagando en ${uiState.selectedRestaurant?.title}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(value = uiState.cardHolderName, onValueChange = viewModel::onCardHolderNameChange, label = { Text("Nombre del Titular") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = uiState.cardNumber, onValueChange = viewModel::onCardNumberChange, label = { Text("Número de Tarjeta") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                OutlinedTextField(value = uiState.cardExpiry, onValueChange = viewModel::onCardExpiryChange, label = { Text("MM/AA") }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(value = uiState.cardCvc, onValueChange = viewModel::onCardCvcChange, label = { Text("CVC") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.confirmOrder() }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Text("Pagar S/ ${"%.2f".format(uiState.totalCost)}")
            }
        }
    }
}