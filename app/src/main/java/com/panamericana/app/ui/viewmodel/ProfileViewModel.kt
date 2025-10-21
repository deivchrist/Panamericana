package com.panamericana.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.panamericana.app.data.UserData
import com.panamericana.app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferencesRepository = UserPreferencesRepository(application)

    val userProfile: StateFlow<UserData> = userPreferencesRepository.userData
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserData()
        )

    fun logOut() {
        viewModelScope.launch {
            userPreferencesRepository.setLoggedIn(false)
        }
    }

    // NUEVA FUNCIÓN: Guarda los cambios del perfil
    fun saveProfileChanges(newFirstName: String, newLastName: String) {
        viewModelScope.launch {
            val currentUser = userProfile.value
            userPreferencesRepository.saveUserData(
                firstName = newFirstName,
                lastName = newLastName,
                email = currentUser.email, // El email no se cambia
                pass = currentUser.password // La contraseña no se cambia
            )
        }
    }
}