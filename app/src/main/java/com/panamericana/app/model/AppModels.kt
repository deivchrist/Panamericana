package com.panamericana.app.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

// Interfaz común para todos los items
sealed interface DiscoverableItem {
    val id: Int
    val title: String
    val shortDescription: String
    val category: String
    val imageResIds: List<Int> // CAMBIO: De List<String> a List<Int>
}

// Modelo para LUGARES
data class TouristSpot(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>, // CAMBIO
    val longDescription: String,
    val bestTimeToVisit: String,
    val rating: Float
) : DiscoverableItem

// Modelo para GASTRONOMÍA
data class Restaurant(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>, // CAMBIO
    val rating: Float,
    val address: String
) : DiscoverableItem

// Modelo para EVENTOS
data class GameEvent(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>, // CAMBIO
    val date: String,
    val location: String,
    val ticketPrice: String
) : DiscoverableItem

// Modelo para TRANSPORTE
data class TransportOption(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>, // CAMBIO
    val cost: String,
    val schedule: String
) : DiscoverableItem

// Modelo para las categorías en HomeScreen
data class Category(
    val title: String,
    val icon: ImageVector
)