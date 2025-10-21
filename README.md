# Proyecto: Panamericana - Guía para los Juegos Panamericanos

## 📖 Descripción del Proyecto

**Panamericana** es una aplicación móvil nativa de Android, desarrollada en Kotlin y Jetpack Compose como proyecto para el curso de Aplicaciones Móviles en Tecsup. El objetivo es ofrecer una guía digital completa e intuitiva para los turistas que asisten a los **Juegos Panamericanos**, facilitando el acceso centralizado a información sobre eventos, puntos de interés, gastronomía y opciones de transporte en Lima.

La aplicación implementa una arquitectura moderna, una interfaz fluida y funcionalidades dinámicas, incluyendo flujos de reserva completos, para mejorar significativamente la experiencia de los visitantes.

---

## ✨ Funcionalidades Implementadas

A continuación se detallan las características clave desarrolladas a lo largo del proyecto:

#### Flujo de Autenticación y Perfil de Usuario
* **Registro y Login:** Pantallas de `Login` y `Registro` completamente funcionales. El registro incluye campos para nombre, apellidos, email y contraseña.
* **Persistencia de Sesión:** Se utiliza **Jetpack DataStore** para guardar de forma segura el estado de la sesión, permitiendo que la app recuerde al usuario y decida si mostrar la pantalla de login o la principal al iniciar.
* **Gestión de Perfil:** Una pantalla de `Perfil` que muestra los datos del usuario que ha iniciado sesión. Incluye una pantalla de `Editar Perfil` para modificar el nombre y apellido, y una función de **Cerrar Sesión** que actualiza el estado y redirige al login.

#### Arquitectura y Gestión de Estado (MVVM)
* **ViewModels por Responsabilidad:** Se implementó una clara separación de la lógica y el estado de la UI utilizando ViewModels dedicados (`HomeViewModel`, `AuthViewModel`, `ProfileViewModel`, `BookingViewModel`).
* **Programación Reactiva:** La comunicación entre la UI y los ViewModels se realiza de forma reactiva mediante el uso de `StateFlow` de Kotlin Coroutines.
* **Modelado de Datos Avanzado:** Se utilizó una **Sealed Interface** (`DiscoverableItem`) para modelar de forma polimórfica las distintas entidades de la aplicación (`TouristSpot`, `Restaurant`, `GameEvent`), permitiendo un manejo de datos robusto y escalable.

#### Navegación Robusta y Dinámica
* **Jetpack Navigation Compose:** Se implementó un sistema de navegación utilizando grafos anidados (`auth_graph` y `main_app_graph`) para separar los flujos de autenticación y de la aplicación principal.
* **Navegación Contextual:** Se realiza el paso de argumentos (como IDs de ítems y nombres de categoría) en las rutas para que las pantallas de lista y detalle muestren contenido dinámico y específico.
* **ViewModels Compartidos:** Se implementó el patrón de ViewModels compartidos y con alcance (`scoped`) al grafo de navegación para gestionar el estado a través de flujos de múltiples pantallas, como en la reserva de entradas.

#### Flujo de Reserva de Entradas para Eventos
* **Selección Detallada:** Flujo completo que permite al usuario seleccionar el **tipo de entrada** (ej. General, VIP) con precios dinámicos.
* **Cálculo en Tiempo Real:** Un contador de asientos interactivo que calcula y actualiza el costo total de la reserva en tiempo real.
* **Simulación de Pago:** Un formulario de pago simulado para completar la experiencia de reserva.
* **Confirmación:** Pantalla final de confirmación con una navegación robusta que limpia el historial y devuelve al usuario a la pantalla de inicio.

#### Interfaz de Usuario (UI) con Jetpack Compose
* **Diseño Moderno:** UI construida 100% con Jetpack Compose y siguiendo los lineamientos de diseño de **Material 3**.
* **Listas de Alto Rendimiento:** Uso eficiente de `LazyColumn`, `LazyRow` y `LazyVerticalGrid` para mostrar grandes cantidades de datos sin afectar el rendimiento.
* **Diseño Adaptativo y Especializado:** Las pantallas de `ListScreen` y `DetailScreen` utilizan lógica condicional (`when`) para adaptar la interfaz y mostrar tarjetas y diseños personalizados para cada categoría de `DiscoverableItem`.
* **Gestión de Imágenes Locales:** Todas las imágenes de la aplicación se gestionan como recursos locales (`drawable`), garantizando un rendimiento óptimo y la funcionalidad completa de la app sin conexión a internet.

---

## 🔄 Flujo de Trabajo y Colaboración

El proyecto se gestionó utilizando un flujo de trabajo profesional basado en **Git y GitHub**:
* **Ramas por Integrante:** Cada miembro del equipo trabajó en una rama de funcionalidad dedicada (`feature-branch`).
* **Pull Requests (PRs):** La integración de código en la rama principal (`main`) se realizó exclusivamente a través de **Pull Requests**, permitiendo la revisión de código por pares y asegurando la estabilidad de la base del proyecto.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Kotlin
* **UI Toolkit:** Jetpack Compose, Material 3
* **Arquitectura:** MVVM (ViewModel, StateFlow, Sealed Interface)
* **Navegación:** Navigation Compose (Grafos Anidados)
* **Asincronía:** Kotlin Coroutines
* **Persistencia de Datos:** Jetpack DataStore
* **Control de Versiones:** Git & GitHub (Flujo de Pull Requests)

---

## 🎨 Prototipo en Figma

El prototipo visual inicial que sirvió como base para el desarrollo se puede consultar en el siguiente enlace:

**➡️ Enlace al Prototipo:** `https://warm-cape-44568504.figma.

## 🏛️ Arquitectura del Proyecto

El proyecto sigue una arquitectura MVVM (Model-View-ViewModel) con una clara separación de responsabilidades, organizada en los siguientes paquetes principales:

```
com.panamericana.app/
├── data/                      
│   ├── SampleData.kt        
│   └── UserPreferencesRepository.kt 
├── model/                    
│   └── AppModels.kt
├── navigation/               
│   └── AppNavigation.kt      
├── ui/                        
│   ├── screens/              
│   │   ├── booking/        
│   │   │   ├── ConfirmationScreen.kt
│   │   │   ├── PaymentScreen.kt
│   │   │   └── SeatSelectionScreen.kt
│   │   ├── restaurant/      
│   │   │   ├── MenuScreen.kt
│   │   │   ├── OrderSummaryScreen.kt
│   │   │   └── RestaurantConfirmationScreen.kt
│   │   ├── DetailScreen.kt
│   │   ├── EditProfileScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── ListScreen.kt
│   │   ├── LoginScreen.kt
│   │   ├── ProfileScreen.kt
│   │   └── RegisterScreen.kt
│   ├── theme/                
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Typography.kt
│   └── viewmodel/             
│       ├── AuthViewModel.kt
│       ├── BookingViewModel.kt
│       ├── HomeViewModel.kt
│       ├── ProfileViewModel.kt
│       └── RestaurantViewModel.kt
└── MainActivity.kt              
```

## 👥 Equipo de Desarrollo

* **Deivid Christian** (Líder Técnico)
* **Bonifacio Zevillano** (Diseñador UI/UX)
* **Sheyla Chuco** (Tester / Documentadora)
