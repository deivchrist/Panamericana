package com.panamericana.app.ui.screens.booking

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
import com.panamericana.app.ui.viewmodel.BookingStatus
import com.panamericana.app.ui.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController, bookingViewModel: BookingViewModel) {
    val uiState by bookingViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.bookingStatus) {
        if (uiState.bookingStatus == BookingStatus.SUCCESS) {
            navController.navigate("confirmation") {
                popUpTo("main_app")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Pago") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            OutlinedTextField(value = uiState.cardHolderName, onValueChange = bookingViewModel::onCardHolderNameChange, label = { Text("Nombre del Titular") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = uiState.cardNumber, onValueChange = bookingViewModel::onCardNumberChange, label = { Text("Número de Tarjeta") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                OutlinedTextField(value = uiState.cardExpiry, onValueChange = bookingViewModel::onCardExpiryChange, label = { Text("MM/AA") }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(value = uiState.cardCvc, onValueChange = bookingViewModel::onCardCvcChange, label = { Text("CVC") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { bookingViewModel.confirmBooking() }, modifier = Modifier.fillMaxWidth()) {
                Text("Pagar S/ ${"%.2f".format(uiState.totalCost)}")
            }
        }
    }
}