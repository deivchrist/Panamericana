package com.panamericana.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.panamericana.app.model.GameEvent
import com.panamericana.app.model.TicketTier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Define el estado completo de la interfaz de usuario para todo el flujo de reserva.
 */
data class BookingUiState(
    val selectedEvent: GameEvent? = null,
    val selectedTier: TicketTier? = null,
    val seatCount: Int = 1,
    val totalCost: Float = 0f,
    val cardNumber: String = "",
    val cardExpiry: String = "",
    val cardCvc: String = "",
    val cardHolderName: String = "",
    val bookingStatus: BookingStatus = BookingStatus.IDLE
)

/**
 * Representa el estado del proceso de reserva (inactivo, exitoso o con error).
 */
enum class BookingStatus { IDLE, SUCCESS, ERROR }

class BookingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Se llama al inicio del flujo. Guarda el evento seleccionado y establece
     * un tipo de entrada y costo por defecto.
     */
    fun selectEvent(event: GameEvent) {
        val defaultTier = event.ticketTiers.firstOrNull()
        _uiState.update {
            it.copy(
                selectedEvent = event,
                selectedTier = defaultTier,
                seatCount = 1,
                totalCost = defaultTier?.price ?: 0f,
                bookingStatus = BookingStatus.IDLE // Resetea el estado al iniciar
            )
        }
    }

    /**
     * Se llama cuando el usuario cambia el tipo de entrada (ej. de 'General' a 'VIP').
     * Actualiza el costo total.
     */
    fun onTierSelected(tier: TicketTier) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTier = tier,
                totalCost = currentState.seatCount * tier.price
            )
        }
    }

    /**
     * Se llama cuando el usuario presiona los botones '+' o '-'.
     * Actualiza la cantidad de asientos y el costo total.
     */
    fun onSeatCountChange(change: Int) {
        _uiState.update { currentState ->
            // El número de asientos estará entre 1 y 10
            val newCount = (currentState.seatCount + change).coerceIn(1, 10)
            currentState.copy(
                seatCount = newCount,
                totalCost = newCount * (currentState.selectedTier?.price ?: 0f)
            )
        }
    }

    // --- Funciones para la pantalla de pago ---

    fun onCardNumberChange(value: String) { _uiState.update { it.copy(cardNumber = value) } }
    fun onCardExpiryChange(value: String) { _uiState.update { it.copy(cardExpiry = value) } }
    fun onCardCvcChange(value: String) { _uiState.update { it.copy(cardCvc = value) } }
    fun onCardHolderNameChange(value: String) { _uiState.update { it.copy(cardHolderName = value) } }

    /**
     * Simula la confirmación del pago y actualiza el estado para
     * provocar la navegación a la pantalla de confirmación.
     */
    fun confirmBooking() {
        // En una app real, aquí se contactaría con una pasarela de pago.
        // Por ahora, simplemente simulamos que el pago fue exitoso.
        if (_uiState.value.cardHolderName.isNotBlank() && _uiState.value.cardNumber.isNotBlank()) {
            _uiState.update { it.copy(bookingStatus = BookingStatus.SUCCESS) }
        } else {
            // Podrías implementar una lógica para mostrar un error si los campos están vacíos
            _uiState.update { it.copy(bookingStatus = BookingStatus.ERROR) }
        }
    }
}