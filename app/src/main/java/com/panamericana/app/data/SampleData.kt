package com.panamericana.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Train
import com.panamericana.app.R
import com.panamericana.app.model.*

val sampleCategories = listOf(
    Category("Lugares", Icons.Default.Place),
    Category("Eventos", Icons.Default.Event),
    Category("Gastronomía", Icons.Default.Dining),
    Category("Transporte", Icons.Default.Train)
)

val allItems: List<DiscoverableItem> = listOf(
    // ========== GASTRONOMÍA (5 items) ==========
    Restaurant(
        id = 1, title = "Central", shortDescription = "Alta Cocina • $$$$", category = "Gastronomía",
        imageResIds = listOf(R.drawable.rest_central), rating = 4.9f, address = "Av. Pedro de Osma 301, Barranco",
        longDescription = "Clasificado como uno de los mejores del mundo, Central ofrece un viaje culinario a través de los ecosistemas del Perú.",
        menu = Menu(
            dishes = listOf(
                MenuItem(101, "Degustación Mundo Mater", "Un viaje por 12 ecosistemas peruanos.", 150.0f),
                MenuItem(102, "Mar y Cordillera", "Vieiras, loche y maca.", 85.0f)
            ),
            drinks = listOf(
                MenuItem(103, "Chilcano de Muña", "Pisco, ginger ale y muña fresca.", 25.0f),
                MenuItem(104, "Agua de la Casa", "Agua filtrada de manantial.", 10.0f)
            )
        )
    ),
    Restaurant(
        id = 2, title = "Maido", shortDescription = "Cocina Nikkei • $$$$", category = "Gastronomía",
        imageResIds = listOf(R.drawable.rest_maido), rating = 4.9f, address = "Calle San Martin 399, Miraflores",
        longDescription = "Fusión de la cocina japonesa y peruana, aclamado mundialmente por su innovación y sabor.",
        menu = Menu(
            dishes = listOf(
                MenuItem(201, "Nigiri a lo Pobre", "Asado de tira, huevo de codorniz y salsa ponzu.", 45.0f),
                MenuItem(202, "Tiradito de las 2 Limas", "Pesca del día con leche de tigre al ají limo.", 65.0f)
            ),
            drinks = listOf(
                MenuItem(203, "Maido Pisco Sour", "Pisco, yuzu y clara de huevo.", 30.0f),
                MenuItem(204, "Té de Jazmín", "Infusión tradicional.", 15.0f)
            )
        )
    ),
    Restaurant(
        id = 3, title = "Isolina", shortDescription = "Criolla • $$", category = "Gastronomía",
        imageResIds = listOf(R.drawable.rest_isolina), rating = 4.7f, address = "Av. San Martín 101, Barranco",
        longDescription = "Un restaurante de ambiente tradicional en el corazón de Barranco, conocido por sus generosas porciones de comida criolla casera.",
        menu = Menu(
            dishes = listOf(
                MenuItem(301, "Lomo Saltado", "Trozos de lomo fino, cebolla, tomate y papas fritas.", 65.0f),
                MenuItem(302, "Seco de Res con Frijoles", "Guiso de carne con culantro, acompañado de frijoles y arroz.", 55.0f)
            ),
            drinks = listOf(
                MenuItem(303, "Chicha Morada", "Jarra de 1L de la bebida tradicional.", 20.0f),
                MenuItem(304, "Cerveza Cusqueña", "Cerveza lager peruana.", 15.0f)
            )
        )
    ),
    Restaurant(
        id = 12, title = "Astrid y Gastón", "Gourmet • $$$$", category = "Gastronomía",
        imageResIds = listOf(R.drawable.rest_astrid_gaston), rating = 4.8f, address = "Av. Paz Soldán 290, San Isidro",
        longDescription = "El restaurante insignia del aclamado chef Gastón Acurio, ubicado en una histórica casona de San Isidro.",
        menu = Menu(
            dishes = listOf(
                MenuItem(401, "Cuy Pekinés", "Una preparación innovadora del tradicional cuy.", 120.0f),
                MenuItem(402, "El Viaje del Ceviche", "Degustación de ceviches de distintas regiones del Perú.", 95.0f)
            ),
            drinks = listOf(
                MenuItem(403, "Vino Blanco, Selección", "Copa de vino blanco recomendado por el sommelier.", 45.0f),
                MenuItem(404, "Cóctel de Autor", "Creación especial de la casa.", 40.0f)
            )
        )
    ),
    Restaurant(
        id = 13, title = "La Lucha Sanguchería", "Sánguches • $", category = "Gastronomía",
        imageResIds = listOf(R.drawable.rest_la_lucha), rating = 4.6f, address = "Av. Jose Larco 999, Miraflores",
        longDescription = "Famosa por sus deliciosos sánguches criollos y jugos de fruta fresca, una parada obligatoria para una comida rápida y sabrosa.",
        menu = Menu(
            dishes = listOf(
                MenuItem(501, "Sánguche de Chicharrón", "Cerdo crujiente con camote frito y salsa criolla.", 22.0f),
                MenuItem(502, "Pavo a la Leña", "Jugoso pavo horneado a la leña con salsa de la casa.", 20.0f)
            ),
            drinks = listOf(
                MenuItem(503, "Jugo de Fresa con Leche", "Jugo natural preparado al momento.", 12.0f),
                MenuItem(504, "Inca Kola", "La gaseosa de sabor nacional.", 7.0f)
            )
        )
    ),

    // ========== LUGARES (5 items) ==========
    TouristSpot(4, "Huaca Pucllana", "Sitio Arqueológico", "Lugares", listOf(R.drawable.lugar_huaca_pucllana), "Una gran pirámide de adobe y arcilla ubicada en el distrito de Miraflores.", "Abril a Noviembre (Noches)", 4.7f, "El restaurante del sitio ofrece una vista espectacular de la pirámide iluminada.", listOf("Reserva el tour nocturno.", "Lleva calzado cómodo.")),
    TouristSpot(5, "Circuito Mágico del Agua", "Atracción Nocturna", "Lugares", listOf(R.drawable.lugar_circuito_agua), "Un impresionante conjunto de trece fuentes ornamentales.", "Todo el año (de noche)", 4.6f, "Posee el Récord Guinness por ser el complejo de fuentes más grande del mundo.", listOf("El espectáculo principal es imperdible.", "Prepárate para mojarte un poco.")),
    TouristSpot(6, "Barranco", "Distrito Bohemio", "Lugares", listOf(R.drawable.lugar_barranco), "Famoso por sus casonas, galerías de arte y el Puente de los Suspiros.", "Todo el año", 4.8f, "Si cruzas el Puente de los Suspiros conteniendo la respiración, se te cumplirá un deseo.", listOf("Explora los murales de arte callejero.", "Visita la Bajada de los Baños.")),
    TouristSpot(14, "Plaza de Armas de Lima", "Centro Histórico", "Lugares", listOf(R.drawable.lugar_plaza_armas), "El corazón de Lima y su centro fundacional, rodeada por la Catedral y el Palacio de Gobierno.", "Todo el año", 4.7f, "La pileta de bronce en el centro de la plaza data de 1651.", listOf("Intenta ver el cambio de guardia al mediodía.", "Visita la Catedral y sus catacumbas.")),
    TouristSpot(15, "Larcomar", "Centro Comercial", "Lugares", listOf(R.drawable.lugar_larcomar), "Un centro comercial construido en un acantilado con una vista espectacular del Océano Pacífico.", "Todo el año, especialmente al atardecer.", 4.6f, "Es uno de los pocos centros comerciales en el mundo construidos en un acantilado.", listOf("Ideal para ver la puesta de sol.", "Conecta con el malecón de Miraflores.")),

// ========== EVENTOS (con tipos de entrada) ==========
    GameEvent(
        id = 7, title = "Final Atletismo 100m", shortDescription = "Atletismo", category = "Eventos",
        imageResIds = listOf(R.drawable.evento_atletismo),
        date = "24 Oct, 19:00h", location = "Estadio Nacional", ticketPrice = "Desde S/ 50",
        longDescription = "La prueba reina del atletismo. Vive la emoción de ver a los atletas más rápidos del continente competir por la medalla de oro.",
        ticketTiers = listOf(TicketTier("General", 50f), TicketTier("Preferencial", 90f), TicketTier("VIP", 150f))
    ),
    GameEvent(
        id = 8, title = "Competencia de Surf", shortDescription = "Surf", category = "Eventos",
        imageResIds = listOf(R.drawable.evento_surf),
        date = "22 Oct, 10:00h", location = "Punta Rocas", ticketPrice = "Acceso Libre",
        longDescription = "Las olas de Punta Rocas son el escenario perfecto para que los mejores surfistas demuestren su talento en busca de la gloria panamericana.",
        ticketTiers = listOf(TicketTier("General", 0f))
    ),
    GameEvent(
        id = 9, title = "Final Voleibol Femenino", shortDescription = "Voleibol", category = "Eventos",
        imageResIds = listOf(R.drawable.evento_voleibol),
        date = "26 Oct, 20:00h", location = "Polideportivo Callao", ticketPrice = "Desde S/ 40",
        longDescription = "Vive la intensidad de la final de voleibol femenino, un evento lleno de emoción y habilidad deportiva de alto nivel.",
        ticketTiers = listOf(TicketTier("General", 40f), TicketTier("Preferencial", 75f))
    ),
    GameEvent(
        id = 16, title = "Ceremonia de Clausura", shortDescription = "Ceremonia", category = "Eventos",
        imageResIds = listOf(R.drawable.evento_clausura),
        date = "30 Oct, 20:00h", location = "Estadio Nacional", ticketPrice = "Desde S/ 80",
        longDescription = "El gran cierre de los Juegos Panamericanos, con un espectáculo de música, luces y la celebración de todos los atletas.",
        ticketTiers = listOf(TicketTier("Tribuna Norte/Sur", 80f), TicketTier("Tribuna Oriente", 120f), TicketTier("Tribuna Occidente", 200f))
    ),
    GameEvent(
        id = 17, title = "Final Natación 400m", shortDescription = "Natación", category = "Eventos",
        imageResIds = listOf(R.drawable.evento_natacion),
        date = "23 Oct, 18:00h", location = "VIDENA", ticketPrice = "Desde S/ 45",
        longDescription = "No te pierdas la final de los 400 metros libres, una de las pruebas más emocionantes y disputadas de la natación.",
        ticketTiers = listOf(TicketTier("General", 45f), TicketTier("VIP", 100f))
    ),

// ... (resto de la lista no cambia) ...
    // ========== TRANSPORTE (5 items) ==========
    TransportOption(10, "Metropolitano", "Ruta Troncal", "Transporte", listOf(R.drawable.trans_metropolitano), "S/ 3.20 por viaje", "5:00h - 23:00h", "El sistema de autobuses de tránsito rápido que recorre Lima de norte a sur. Conecta con estaciones clave cerca de varias sedes."),
    TransportOption(11, "Línea 1 del Metro", "Tren Eléctrico", "Transporte", listOf(R.drawable.trans_metro), "S/ 1.50 por viaje", "5:30h - 23:00h", "Conecta distritos clave de la ciudad de este a oeste y es una opción rápida para movilizarse a sedes como la VIDENA."),
    TransportOption(18, "Corredores (Azul/Rojo)", "Rutas Complementarias", "Transporte", listOf(R.drawable.trans_corredor), "S/ 2.20 por viaje", "5:00h - 23:00h", "Sistema de autobuses que recorren avenidas principales como Javier Prado, Arequipa y Wilson, complementando al Metropolitano."),
    TransportOption(19, "Taxis de Aplicativo", "Uber, Cabify, Didi", "Transporte", listOf(R.drawable.trans_taxi_app), "Varía por distancia", "24 horas", "La opción más cómoda para viajes punto a punto. Se recomienda usar aplicativos por seguridad y tarifas transparentes."),
    TransportOption(20, "Aeropuerto Jorge Chávez", "Punto de Llegada/Salida", "Transporte", listOf(R.drawable.trans_aeropuerto), "N/A", "24 horas", "El principal terminal aéreo de Lima. Conecta con la ciudad a través de taxis oficiales, buses y servicios de shuttle.")
)