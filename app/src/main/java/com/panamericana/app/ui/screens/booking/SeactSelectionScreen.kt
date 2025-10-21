package com.panamericana.app.ui.screens.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.panamericana.app.model.TicketTier
import com.panamericana.app.ui.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatSelectionScreen(navController: NavController, bookingViewModel: BookingViewModel) {
    val uiState by bookingViewModel.uiState.collectAsState()
    val event = uiState.selectedEvent ?: return

    // Scaffold nos permite tener una barra superior y una barra inferior fija
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reservar Entradas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            // Barra inferior con el resumen y el botón de pago
            BottomBar(
                totalCost = uiState.totalCost,
                onContinueClick = { navController.navigate("payment") }
            )
        }
    ) { padding ->
        // LazyColumn para que la pantalla pueda crecer si es necesario
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 100.dp) // Espacio para que no choque con la barra inferior
        ) {
            // 1. Imagen del Evento
            item {
                Image(
                    painter = painterResource(id = event.imageResIds.first()),
                    contentDescription = event.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // 2. Información del Evento
            item {
                Column(Modifier.padding(16.dp)) {
                    Text(event.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(event.location, style = MaterialTheme.typography.titleMedium)
                }
            }

            // 3. Card para la selección de tipo de entrada
            item {
                BookingStepCard(step = "1", title = "Selecciona tu tipo de entrada") {
                    Column {
                        event.ticketTiers.forEach { tier ->
                            TicketTierRow(
                                tier = tier,
                                isSelected = uiState.selectedTier == tier,
                                onTierSelected = { bookingViewModel.onTierSelected(tier) }
                            )
                        }
                    }
                }
            }

            // 4. Card para la cantidad de asientos
            item {
                BookingStepCard(step = "2", title = "Elige la cantidad") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        FilledTonalIconButton(onClick = { bookingViewModel.onSeatCountChange(-1) }) {
                            Icon(Icons.Default.Remove, "Quitar")
                        }
                        Text(
                            text = "${uiState.seatCount}",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        FilledTonalIconButton(onClick = { bookingViewModel.onSeatCountChange(1) }) {
                            Icon(Icons.Default.Add, "Añadir")
                        }
                    }
                }
            }
        }
    }
}

// --- COMPOSABLES DE AYUDA PARA EL NUEVO DISEÑO ---

@Composable
private fun BookingStepCard(step: String, title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("$step. $title", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
private fun TicketTierRow(tier: TicketTier, isSelected: Boolean, onTierSelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(selected = isSelected, onClick = onTierSelected)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = onTierSelected)
        Spacer(modifier = Modifier.width(16.dp))
        Text(tier.name, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        Text("S/ ${"%.2f".format(tier.price)}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun BottomBar(totalCost: Float, onContinueClick: () -> Unit) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Total a Pagar", style = MaterialTheme.typography.bodyMedium)
                Text("S/ ${"%.2f".format(totalCost)}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
            Button(onClick = onContinueClick) {
                Text("Continuar al Pago")
            }
        }
    }
}