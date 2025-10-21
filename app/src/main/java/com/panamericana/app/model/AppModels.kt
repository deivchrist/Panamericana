package com.panamericana.app.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface DiscoverableItem {
    val id: Int
    val title: String
    val shortDescription: String
    val category: String
    val imageResIds: List<Int>
}

data class TouristSpot(
    override val id: Int, override val title: String, override val shortDescription: String, override val category: String,
    @DrawableRes override val imageResIds: List<Int>,
    val longDescription: String,
    val bestTimeToVisit: String,
    val rating: Float,
    val funFact: String,
    val visitorTips: List<String>
) : DiscoverableItem

data class Restaurant(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>,
    val rating: Float,
    val address: String,
    val longDescription: String,
    val menu: Menu
) : DiscoverableItem

data class GameEvent(
    override val id: Int,
    override val title: String,
    override val shortDescription: String,
    override val category: String,
    @DrawableRes override val imageResIds: List<Int>,
    val date: String,
    val location: String,
    val ticketPrice: String,
    val longDescription: String,
    // CAMBIO: Eliminamos pricePerSeat y availableSeats de aquí
    val ticketTiers: List<TicketTier> // <-- AÑADIDO: Una lista de tipos de entrada
) : DiscoverableItem


data class TransportOption(
    override val id: Int, override val title: String, override val shortDescription: String, override val category: String,
    @DrawableRes override val imageResIds: List<Int>,
    val cost: String,
    val schedule: String,
    val longDescription: String // <-- CAMPO AÑADIDO
) : DiscoverableItem

data class Category(
    val title: String,
    val icon: ImageVector
)

data class TicketTier(
    val name: String, // ej. "General", "VIP"
    val price: Float
)

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float
)

data class Menu(
    val dishes: List<MenuItem>,
    val drinks: List<MenuItem>
)

