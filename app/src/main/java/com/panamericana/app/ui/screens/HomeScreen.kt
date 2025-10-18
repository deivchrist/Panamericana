package com.panamericana.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.panamericana.app.data.sampleCategories
import com.panamericana.app.data.sampleRecommendations
import com.panamericana.app.model.Category
import com.panamericana.app.model.Recommendation

@Composable
fun HomeScreen(navController: NavController) {
    // El Column permite apilar elementos verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(24.dp))
        CategoriesSection()
        Spacer(modifier = Modifier.height(24.dp))
        RecommendationsSection(navController = navController)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hola, Deivid", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = { /* TODO: Navegar al perfil */ }) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil")
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Buscar eventos, lugares, comida...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun CategoriesSection() {
    Column {
        Text(text = "Categorías", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleCategories) { category ->
                CategoryCard(category = category)
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Card(
        modifier = Modifier.size(width = 160.dp, height = 100.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = category.icon, contentDescription = category.name, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category.name, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun RecommendationsSection(navController: NavController) {
    Column {
        Text(text = "Recomendado para ti", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleRecommendations) { recommendation ->
                RecommendationCard(
                    recommendation = recommendation,
                    onClick = {
                        // RF14: Navegar a la pantalla de detalle al hacer clic
                        navController.navigate("detail")
                    }
                )
            }
        }
    }
}

@Composable
fun RecommendationCard(recommendation: Recommendation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable(onClick = onClick), // RF12: Botón interactivo (toda la tarjeta)
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            AsyncImage(
                model = recommendation.imageUrl,
                contentDescription = recommendation.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = recommendation.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = recommendation.subtitle, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = recommendation.rating.toString(), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}