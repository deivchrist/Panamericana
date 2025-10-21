package com.panamericana.app.ui.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun ConfirmationScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Éxito",
                modifier = Modifier.size(100.dp),
                tint = Color(0xFF4CAF50) // Un verde más estándar de Material Design
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("¡Reserva Confirmada!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                // CORRECCIÓN: Navegación robusta para volver al inicio y limpiar el historial
                navController.navigate("home") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }) {
                Text("Volver al Inicio")
            }
        }
    }
}