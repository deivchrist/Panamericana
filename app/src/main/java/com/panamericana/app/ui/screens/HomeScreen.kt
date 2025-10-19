package com.panamericana.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.panamericana.app.R // Asegúrate de que este import exista
import com.panamericana.app.data.sampleCategories
import com.panamericana.app.model.Place
import com.panamericana.app.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val places by viewModel.places.collectAsState()

    // Scaffold nos da la estructura profesional con barra superior, contenido, etc.
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Carga el logo desde la carpeta res/drawable
                    // Asegúrate de que tu logo se llame 'logo.png' o cambia el nombre aquí
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo de la App",
                        modifier = Modifier.height(32.dp)
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Navegar a la pantalla de perfil */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        // LazyColumn es la forma correcta y más eficiente de crear listas verticales
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // Padding para que el contenido no quede detrás de la TopBar
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Cada sección de la pantalla es ahora un 'item' en la lista perezosa
            item {
                SearchBar()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                CategoriesSection(navController = navController)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                RecommendationsSection(navController = navController, places = places)
            }
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "", onValueChange = {},
        placeholder = { Text("Buscar eventos, lugares, comida...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp)
    )
}


@Composable
fun CategoriesSection(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            sampleCategories.forEach { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { navController.navigate("list/${category.title}") }
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = category.title,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = category.title, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun RecommendationsSection(navController: NavController, places: List<Place>) {
    Column {
        Text(
            text = "Recomendado para ti",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(places) { place ->
                PlaceCard(place = place, onClick = {
                    navController.navigate("detail/${place.id}")
                })
            }
        }
    }
}

@Composable
fun PlaceCard(place: Place, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(240.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = place.imageUrls.first(),
                contentDescription = place.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = place.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(text = place.shortDescription, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = place.rating.toString(), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}