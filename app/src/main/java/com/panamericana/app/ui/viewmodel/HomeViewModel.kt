package com.panamericana.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panamericana.app.data.allItems
import com.panamericana.app.model.DiscoverableItem
import kotlinx.coroutines.flow.*

class HomeViewModel : ViewModel() {

    private val _allItems = MutableStateFlow(allItems)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Flujo para la sección "Recomendado para ti"
    val recommendedItems: StateFlow<List<DiscoverableItem>> = _allItems
        .combine(_searchQuery) { items, query ->
            if (query.isBlank()) {
                // Muestra solo Lugares y Gastronomía como "Recomendados"
                items.filter { it.category == "Lugares" || it.category == "Gastronomía" }
            } else {
                items.filter { it.title.contains(query, ignoreCase = true) }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Flujo específico para la sección "Eventos Próximos"
    val eventItems: StateFlow<List<DiscoverableItem>> = _allItems.map { items ->
        items.filter { it.category == "Eventos" }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun getItemById(id: Int): DiscoverableItem? {
        return _allItems.value.find { it.id == id }
    }

    fun getItemsByCategory(categoryName: String): List<DiscoverableItem> {
        return _allItems.value.filter { it.category.equals(categoryName, ignoreCase = true) }
    }
}