package com.panamericana.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.panamericana.app.data.samplePlaces
import com.panamericana.app.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _places = MutableStateFlow(samplePlaces)
    val places: StateFlow<List<Place>> = _places

    fun getPlaceById(id: Int): Place? {
        return _places.value.find { it.id == id }
    }

    fun getPlacesByCategory(categoryName: String): List<Place> {
        return _places.value.filter { it.category == categoryName }
    }
}