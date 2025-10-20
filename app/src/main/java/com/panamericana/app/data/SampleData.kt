package com.panamericana.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Train
import com.panamericana.app.R // El único import de 'R' que necesitas
import com.panamericana.app.model.*

val sampleCategories = listOf(
    Category("Lugares", Icons.Default.Place),
    Category("Eventos", Icons.Default.Event),
    Category("Gastronomía", Icons.Default.Dining),
    Category("Transporte", Icons.Default.Train)
)

val allItems: List<DiscoverableItem> = listOf(
    // ========== GASTRONOMÍA (5 items) ==========
    Restaurant(1, "Central", "Alta Cocina • $$$$", "Gastronomía", listOf(R.drawable.rest_central), 4.9f, "Av. Pedro de Osma 301, Barranco"),
    Restaurant(2, "Maido", "Cocina Nikkei • $$$$", "Gastronomía", listOf(R.drawable.rest_maido), 4.9f, "Calle San Martin 399, Miraflores"),
    Restaurant(3, "Isolina", "Criolla • $$", "Gastronomía", listOf(R.drawable.rest_isolina), 4.7f, "Av. San Martín 101, Barranco"),
    Restaurant(12, "Astrid y Gastón", "Gourmet • $$$$", "Gastronomía", listOf(R.drawable.rest_astrid_gaston), 4.8f, "Av. Paz Soldán 290, San Isidro"),
    Restaurant(13, "La Lucha Sanguchería", "Sánguches • $", "Gastronomía", listOf(R.drawable.rest_la_lucha), 4.6f, "Av. Jose Larco 999, Miraflores"),

    // ========== LUGARES (5 items) ==========
    TouristSpot(4, "Huaca Pucllana", "Sitio Arqueológico", "Lugares", listOf(R.drawable.lugar_huaca_pucllana), "Una gran pirámide de adobe y arcilla.", "Abril a Noviembre (Noches)", 4.7f),
    TouristSpot(5, "Circuito Mágico del Agua", "Atracción Nocturna", "Lugares", listOf(R.drawable.lugar_circuito_agua), "Un impresionante conjunto de trece fuentes ornamentales.", "Todo el año (de noche)", 4.6f),
    TouristSpot(6, "Barranco", "Distrito Bohemio", "Lugares", listOf(R.drawable.lugar_barranco), "Famoso por sus casonas, galerías de arte y el Puente de los Suspiros.", "Todo el año", 4.8f),
    TouristSpot(14, "Plaza de Armas de Lima", "Centro Histórico", "Lugares", listOf(R.drawable.lugar_plaza_armas), "El corazón de Lima, rodeada por la Catedral y el Palacio de Gobierno.", "Todo el año", 4.7f),
    TouristSpot(15, "Larcomar", "Centro Comercial", "Lugares", listOf(R.drawable.lugar_larcomar), "Un centro comercial construido en un acantilado con vista al Océano Pacífico.", "Todo el año", 4.6f),

    // ========== EVENTOS (5 items) ==========
    GameEvent(7, "Final Atletismo 100m", "Atletismo", "Eventos", listOf(R.drawable.evento_atletismo), "24 Oct, 19:00h", "Estadio Nacional", "Desde S/ 50"),
    GameEvent(8, "Competencia de Surf", "Surf", "Eventos", listOf(R.drawable.evento_surf), "22 Oct, 10:00h", "Punta Rocas", "Acceso Libre"),
    GameEvent(9, "Final Voleibol Femenino", "Voleibol", "Eventos", listOf(R.drawable.evento_voleibol), "26 Oct, 20:00h", "Polideportivo Callao", "Desde S/ 40"),
    GameEvent(16, "Ceremonia de Clausura", "Ceremonia", "Eventos", listOf(R.drawable.evento_clausura), "30 Oct, 20:00h", "Estadio Nacional", "Desde S/ 80"),
    GameEvent(17, "Final Natación 400m", "Natación", "Eventos", listOf(R.drawable.evento_natacion), "23 Oct, 18:00h", "VIDENA", "Desde S/ 45"),

    // ========== TRANSPORTE (5 items) ==========
    TransportOption(10, "Metropolitano", "Ruta Troncal", "Transporte", listOf(R.drawable.trans_metropolitano), "S/ 3.20 por viaje", "5:00h - 23:00h"),
    TransportOption(11, "Línea 1 del Metro", "Tren Eléctrico", "Transporte", listOf(R.drawable.trans_metro), "S/ 1.50 por viaje", "5:30h - 23:00h"),
    TransportOption(18, "Corredores (Azul/Rojo)", "Rutas Complementarias", "Transporte", listOf(R.drawable.trans_corredor), "S/ 2.20 por viaje", "5:00h - 23:00h"),
    TransportOption(19, "Taxis de Aplicativo", "Uber, Cabify, Didi", "Transporte", listOf(R.drawable.trans_taxi_app), "Varía por distancia", "24 horas"),
    TransportOption(20, "Aeropuerto Jorge Chávez", "Punto de Llegada/Salida", "Transporte", listOf(R.drawable.trans_aeropuerto), "N/A", "24 horas")
)