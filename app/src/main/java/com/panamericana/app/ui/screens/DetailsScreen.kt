package com.panamericana.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pantalla de Detalle", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        // Placeholder para la imagen
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            Text("Aquí iría una imagen", modifier = Modifier.align(Alignment.Center))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Esta es la descripción detallada del lugar o evento seleccionado. Contiene toda la información relevante que el turista necesita saber.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* TODO: Añadir a favoritos */ }) {
            Text(text = "Añadir a Favoritos")
        }
    }
}