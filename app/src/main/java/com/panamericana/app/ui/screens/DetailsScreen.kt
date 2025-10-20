package com.panamericana.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.panamericana.app.R
import com.panamericana.app.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, item: DiscoverableItem) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                    // CAMBIO: Se usa Image con painterResource para la imagen local
                    Image(
                        painter = painterResource(id = item.imageResIds.first()),
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                }
            }

            item {
                when (item) {
                    is TouristSpot -> TouristSpotDetailContent(item = item)
                    is Restaurant -> RestaurantDetailContent(item = item)
                    is GameEvent -> GameEventDetailContent(item = item)
                    is TransportOption -> TransportDetailContent(item = item)
                }
            }
        }
    }
}

// COMPOSABLES DE DETALLE PARA CADA CATEGORÍA

@Composable
private fun TouristSpotDetailContent(item: TouristSpot) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Star, text = "${item.rating} • ${item.category}")
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text("Mejor Época para Visitar", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(item.bestTimeToVisit, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Acerca de", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(item.longDescription, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun GameEventDetailContent(item: GameEvent) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.CalendarToday, text = item.date)
        InfoRow(icon = Icons.Default.LocationOn, text = item.location)
        InfoRow(icon = Icons.Default.ConfirmationNumber, text = "Entradas ${item.ticketPrice}")
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { /* Lógica de reserva */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Reservar Entrada")
        }
    }
}

@Composable
private fun RestaurantDetailContent(item: Restaurant) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Star, text = "${item.rating} • ${item.shortDescription}")
        InfoRow(icon = Icons.Default.LocationOn, text = item.address)
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { /* Lógica para ver menú o reservar */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Ver Menú / Reservar")
        }
    }
}

@Composable
private fun TransportDetailContent(item: TransportOption) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.AttachMoney, text = "Costo: ${item.cost}")
        InfoRow(icon = Icons.Default.Schedule, text = "Horario: ${item.schedule}")
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text("Ruta", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(item.shortDescription, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}