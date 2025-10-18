package com.panamericana.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Train
import com.panamericana.app.model.Category
import com.panamericana.app.model.Recommendation

val sampleCategories = listOf(
    Category("Lugares", Icons.Default.Place),
    Category("Eventos", Icons.Default.Event),
    Category("Gastronomía", Icons.Default.Dining),
    Category("Transporte", Icons.Default.Train)
)

val sampleRecommendations = listOf(
    Recommendation(
        "Restaurante Central",
        "Alta Cocina • $$$",
        4.9f,
        "https://d3rr2gvhjw0wwy.cloudfront.net/uploads/mandators/54121/images/2972999/big_Lima-Food-Tour-Ceviche.jpg"
    ),
    Recommendation(
        "Machu Picchu",
        "Sitio Arqueológico",
        5.0f,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Machu_Picchu%2C_Peru.jpg/1200px-Machu_Picchu%2C_Peru.jpg"
    ),
    Recommendation(
        "La Lucha Sanguchería",
        "Sánguches • $$",
        4.7f,
        "https://media-cdn.tripadvisor.com/media/photo-s/0e/23/9c/13/photo1jpg.jpg"
    )
)