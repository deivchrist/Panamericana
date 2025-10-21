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
import com.panamericana.app.ui.viewmodel.BookingViewModel
import com.panamericana.app.ui.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    item: DiscoverableItem,
    bookingViewModel: BookingViewModel,
    restaurantViewModel: RestaurantViewModel
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
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
                    is Restaurant -> RestaurantDetailContent(
                        item = item,
                        onReserveClick = {
                            restaurantViewModel.selectRestaurant(item)
                            navController.navigate("menu")
                        }
                    )
                    is GameEvent -> GameEventDetailContent(
                        item = item,
                        onReserveClick = {
                            bookingViewModel.selectEvent(item)
                            navController.navigate("seat_selection")
                        }
                    )
                    is TransportOption -> TransportDetailContent(item = item)
                }
            }
        }
    }
}

// --- COMPOSABLES DE DETALLE PARA CADA CATEGORÍA ---

@Composable
private fun TouristSpotDetailContent(item: TouristSpot) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Star, text = "${item.rating} • ${item.category}")
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        InfoSection("Acerca de", icon = Icons.Default.Info) {
            Text(item.longDescription, style = MaterialTheme.typography.bodyLarge)
        }
        InfoSection("Mejor Época para Visitar", icon = Icons.Default.CalendarToday) {
            Text(item.bestTimeToVisit, style = MaterialTheme.typography.bodyLarge)
        }
        InfoSection("Curiosidad", icon = Icons.Default.Lightbulb) {
            Text(item.funFact, style = MaterialTheme.typography.bodyLarge)
        }
        InfoSection("Tips para tu Visita", icon = Icons.Default.Check) {
            item.visitorTips.forEach { tip -> InfoRow(icon = Icons.Default.ChevronRight, text = tip) }
        }
    }
}

@Composable
private fun GameEventDetailContent(item: GameEvent, onReserveClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.SportsScore, text = item.shortDescription)
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        InfoSection("Información del Evento", icon = Icons.Default.Info) {
            InfoRow(icon = Icons.Default.CalendarToday, text = "Fecha: ${item.date}")
            InfoRow(icon = Icons.Default.LocationOn, text = "Lugar: ${item.location}")
            InfoRow(icon = Icons.Default.ConfirmationNumber, text = "Entradas: ${item.ticketPrice}")
        }
        InfoSection("Descripción", icon = Icons.Default.Description) {
            Text(item.longDescription, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onReserveClick, modifier = Modifier.fillMaxWidth()) {
            Text("Comprar / Reservar Entrada")
        }
    }
}

@Composable
private fun RestaurantDetailContent(item: Restaurant, onReserveClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Star, text = "${item.rating} • ${item.shortDescription}")
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        InfoSection("Descripción", icon = Icons.Default.Description) {
            Text(item.longDescription, style = MaterialTheme.typography.bodyLarge)
        }
        InfoSection("Ubicación", icon = Icons.Default.LocationOn) {
            Text(item.address, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onReserveClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ver Menú / Reservar")
        }
    }
}

@Composable
private fun TransportDetailContent(item: TransportOption) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        InfoRow(icon = Icons.Default.Route, text = item.shortDescription)
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        InfoSection("Detalles del Servicio", icon = Icons.Default.Info) {
            InfoRow(icon = Icons.Default.AttachMoney, text = "Costo: ${item.cost}")
            InfoRow(icon = Icons.Default.Schedule, text = "Horario: ${item.schedule}")
        }
        InfoSection("Descripción", icon = Icons.Default.Description) {
            Text(item.longDescription, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

// --- COMPOSABLES DE AYUDA REUTILIZABLES ---
@Composable
private fun InfoSection(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(start = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}