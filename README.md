# Proyecto: Panamericana - Guía para los Juegos Panamericanos

## 📖 Descripción del Proyecto

**Panamericana** es una aplicación móvil, desarrollada como proyecto para el curso de Aplicaciones Móviles en Tecsup. El objetivo es construir una guía digital indispensable para los turistas que asisten a los **Juegos Panamericanos**, facilitando el acceso centralizado a información sobre eventos, puntos de interés, gastronomía, transporte y experiencias locales para mejorar significativamente su estadía.

---

## ✨ Características Propuestas

Para enriquecer la experiencia del turista, el alcance del proyecto contempla la implementación de las siguientes funcionalidades:

* **Calendario Interactivo de Competencias:** Cronograma de eventos de los Juegos Panamericanos con filtros por deporte, fecha o país, y notificaciones para eventos guardados.
* **Mapa Inteligente:** Mapa integrado con las sedes de los juegos y puntos turísticos, mostrando rutas de transporte público en tiempo real.
* **Rutas Gastronómicas:** Guías temáticas predefinidas (ej. "Ruta del Ceviche") que recomiendan locales y recorridos culinarios.
* **Asistente de Idioma:** Traductor básico y una guía de frases locales útiles para facilitar la interacción de los turistas.
* **Directorio de Seguridad:** Sección de acceso rápido con contactos de emergencia, ubicación de embajadas y consejos de seguridad.
* **Sistema de Reseñas:** Plataforma para que los usuarios califiquen y dejen comentarios sobre lugares, creando una comunidad con información confiable.
* **Conversor de Moneda:** Herramienta en tiempo real para convertir divisas a Soles peruanos (PEN).
* **Guía de Compras:** Mapa especializado que destaca mercados de artesanías y tiendas para la compra de souvenirs.
* **Agenda Cultural:** Listado de eventos externos a los juegos, como conciertos, exposiciones y festivales locales.
* **Notificaciones Inteligentes:** Alertas push personalizadas sobre resultados, cambios de horario o recomendaciones basadas en la ubicación.

---

## 🗺️ Flujo de Navegación Principal

El prototipo inicial se enfoca en el siguiente flujo de usuario, demostrando la navegación entre las pantallas clave de la aplicación.

#### Escenario 1: Descubrimiento y Guardado de un Favorito

1.  **Inicio:** El usuario accede a la **Pantalla Principal (Home)**, donde se presentan las categorías de contenido.
2.  **Selección:** Elige una categoría de interés, como **"Gastronomía"**.
3.  **Exploración:** La aplicación muestra una **Pantalla de Lista** con los restaurantes disponibles.
4.  **Consulta:** Al seleccionar un restaurante, se navega a la **Pantalla de Detalle** con su información completa.
5.  **Acción:** El usuario guarda el restaurante en su lista personal mediante el botón **"Añadir a Favoritos"**.
6.  **Retorno:** El usuario navega hacia atrás, regresando a la lista y posteriormente a la pantalla principal.

#### Escenario 2: Consulta de Favoritos

1.  **Acceso:** Desde la **Pantalla Principal**, el usuario selecciona el ícono de **"Favoritos"**.
2.  **Visualización:** La aplicación muestra la **Pantalla de Favoritos**, listando todos los elementos que el usuario ha guardado previamente.
3.  **Retorno:** El usuario puede regresar a la pantalla principal para continuar explorando.

---

## 🛠️ Stack Tecnológico

* **Lenguaje de Programación:** Kotlin
* **Toolkit de UI:** Jetpack Compose
* **Arquitectura:** MVVM (Model-View-ViewModel)
* **Diseño y Prototipado:** Figma
* **Control de Versiones:** Git / GitHub

---

## 🎨 Prototipo en Figma

El diseño de la interfaz y la experiencia de usuario (UI/UX), junto con el prototipo interactivo, se encuentran disponibles en el siguiente enlace de Figma:

**➡️ Enlace al Prototipo:** `https://warm-cape-44568504.figma.

##  Arquitectura del Proyecto

```
com.proyecto.panamericana/
├── navigation/              
│   ├── AppNavigation.kt
├── ui/                      
│   ├── MainScreen.kt        
│   └── screens/             
│       ├── DetailScreem.kt
│       ├── FavoriteScren.kt
│       ├── HomeScreen.kt
└── MainActivity.kt          

```

## 👥 Equipo de Desarrollo

* **Deivid Christian** (Líder Técnico)
* **Bonifacio Zevillano** (Diseñador UI/UX)
* **Sheyla Chuco** (Tester / Documentadora)
