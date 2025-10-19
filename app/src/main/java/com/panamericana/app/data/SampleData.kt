package com.panamericana.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Train
import com.panamericana.app.model.Category
import com.panamericana.app.model.Place

val sampleCategories = listOf(
    Category("Lugares", Icons.Default.Place),
    Category("Eventos", Icons.Default.Event),
    Category("Gastronomía", Icons.Default.Dining),
    Category("Transporte", Icons.Default.Train)
)

val samplePlaces = listOf(
    Place(
        id = 1, title = "Restaurante Central", shortDescription = "Alta Cocina • $$$",
        longDescription = "Constantemente clasificado como uno de los mejores del mundo, Central ofrece un viaje culinario a través de los ecosistemas del Perú.",
        rating = 4.9f, category = "Gastronomía",
        imageUrls = listOf("https://d3rr2gvhjw0wwy.cloudfront.net/uploads/mandators/54121/images/2972999/big_Lima-Food-Tour-Ceviche.jpg", "https://media.cntraveller.com/photos/611be86b1a33575b810a7431/16:9/w_2560,c_limit/central-lima-peru-conde-nast-traveller-25feb15-pr.jpg")
    ),
    Place(
        id = 2, title = "Huaca Pucllana", shortDescription = "Sitio Arqueológico",
        longDescription = "Una gran pirámide de adobe y arcilla ubicada en el distrito de Miraflores, que sirvió como un importante centro ceremonial de la cultura Lima.",
        rating = 4.7f, category = "Lugares",
        imageUrls = listOf("https://denomades.s3.us-west-2.amazonaws.com/blog/wp-content/uploads/2020/08/11171013/huaca-pucllana-noche-1024x683.jpg", "https://www.wamanadventures.com/blog/wp-content/uploads/2021/08/huaca-pucllana-1.jpg")
    ),
    Place(
        id = 3, title = "Estadio Nacional", shortDescription = "Evento Deportivo",
        longDescription = "El principal estadio del Perú, sede de los partidos de la selección nacional de fútbol y de los eventos más importantes de los Juegos Panamericanos.",
        rating = 4.8f, category = "Eventos",
        imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/4/49/Estadio_Nacional_de_Lima_-_Fachada_occidente.jpg", "https://img.goraymi.com/2018/09/12/2c8033c46a6f1165a6e706c45f49e7b2_lg.jpg")
    ),
    Place(
        id = 4, title = "Maido", shortDescription = "Cocina Nikkei • $$$$",
        longDescription = "Una fusión de la cocina japonesa y peruana, Maido es otro de los restaurantes peruanos aclamados mundialmente por su innovación y sabor.",
        rating = 4.9f, category = "Gastronomía",
        imageUrls = listOf("https://cdn.shortpixel.ai/spai/q_lossy+w_1024+h_683+to_auto/proabogados.com/wp-content/uploads/2023/04/restaurante-maido.jpg", "https://media.gq.com.mx/photos/6350280f3319a9a01662580a/16:9/w_2560%2Cc_limit/Maido-restaurante.jpg")
    ),
    Place(
        id = 5, title = "Circuito Mágico del Agua", shortDescription = "Atracción Turística",
        longDescription = "Un impresionante conjunto de trece fuentes ornamentales, cibernéticas e interactivas donde el agua, la música, la luz y las imágenes se mezclan.",
        rating = 4.6f, category = "Lugares",
        imageUrls = listOf("https://media.tacdn.com/media/attractions-splice-spp-674x446/06/71/34/26.jpg", "https://www.entradasparquesdelima.com/wp-content/uploads/2023/12/parque-de-las-aguas.png")
    ),
    Place(
        id = 6, title = "Barranco", shortDescription = "Distrito Bohemio",
        longDescription = "Conocido por su ambiente bohemio, Barranco es famoso por sus casonas, galerías de arte, bares con música en vivo y el icónico Puente de los Suspiros.",
        rating = 4.8f, category = "Lugares",
        imageUrls = listOf("https://www.anywhere.com/peru/destinations/lima/attractions/barranco/packages/barranco-2-anywhere-com.jpg", "https://mundukos.com/wp-content/uploads/2022/07/Puente-de-los-suspiros-en-Barranco-Lima-Peru-1.jpg")
    )
)
