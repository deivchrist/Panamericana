package com.panamericana.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.panamericana.app.model.MenuItem
import com.panamericana.app.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Define el estado del proceso de la orden
enum class OrderStatus { IDLE, SUCCESS, ERROR }

// Define el estado completo de la UI para el flujo
data class OrderUiState(
    val selectedRestaurant: Restaurant? = null,
    val cart: Map<MenuItem, Int> = emptyMap(),
    val totalCost: Float = 0f,
    val cardNumber: String = "",
    val cardExpiry: String = "",
    val cardCvc: String = "",
    val cardHolderName: String = "",
    val orderStatus: OrderStatus = OrderStatus.IDLE
)

class RestaurantViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    fun selectRestaurant(restaurant: Restaurant) {
        _uiState.value = OrderUiState(selectedRestaurant = restaurant)
    }

    fun addItemToCart(item: MenuItem) {
        _uiState.update { currentState ->
            val newCart = currentState.cart.toMutableMap()
            newCart[item] = (newCart[item] ?: 0) + 1
            currentState.copy(cart = newCart, totalCost = calculateTotal(newCart))
        }
    }

    fun removeItemFromCart(item: MenuItem) {
        _uiState.update { currentState ->
            val newCart = currentState.cart.toMutableMap()
            val currentQty = newCart[item] ?: 0
            if (currentQty > 1) {
                newCart[item] = currentQty - 1
            } else {
                newCart.remove(item)
            }
            currentState.copy(cart = newCart, totalCost = calculateTotal(newCart))
        }
    }

    private fun calculateTotal(cart: Map<MenuItem, Int>): Float {
        return cart.entries.sumOf { (item, qty) -> (item.price * qty).toDouble() }.toFloat()
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
    fun confirmOrder() {
        if (_uiState.value.cardHolderName.isNotBlank() && _uiState.value.cardNumber.isNotBlank()) {
            _uiState.update { it.copy(orderStatus = OrderStatus.SUCCESS) }
        } else {
            _uiState.update { it.copy(orderStatus = OrderStatus.ERROR) }
        }
    }
}