package com.panamericana.app.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val title: String,
    val icon: ImageVector
)

data class Place(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val rating: Float,
    val category: String,
    val imageUrls: List<String>
)