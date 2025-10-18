package com.panamericana.app.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val name: String,
    val icon: ImageVector
)

data class Recommendation(
    val title: String,
    val subtitle: String,
    val rating: Float,
    val imageUrl: String
)