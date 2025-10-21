package com.panamericana.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.panamericana.app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferencesRepository = UserPreferencesRepository(application)

    // Estados para los campos de texto de la UI
    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // Estado para saber si el login fue exitoso, erróneo o está inactivo
    private val _loginState = MutableStateFlow<LoginStatus>(LoginStatus.IDLE)
    val loginState: StateFlow<LoginStatus> = _loginState.asStateFlow()

    // Estado para notificar a la UI que el registro se completó
    private val _registrationComplete = MutableStateFlow(false)
    val registrationComplete: StateFlow<Boolean> = _registrationComplete.asStateFlow()

    // Funciones para actualizar los estados desde la UI
    fun onFirstNameChange(name: String) { _firstName.value = name }
    fun onLastNameChange(lastName: String) { _lastName.value = lastName }
    fun onEmailChange(newEmail: String) { _email.value = newEmail }
    fun onPasswordChange(newPass: String) { _password.value = newPass }

    /**
     * Comprueba las credenciales ingresadas contra las guardadas.
     * Si son correctas, marca la sesión como iniciada (isLoggedIn = true).
     */
    fun login() {
        viewModelScope.launch {
            val savedData = userPreferencesRepository.userData.first()
            if (_email.value.isNotBlank() && _email.value == savedData.email && _password.value.isNotBlank() && _password.value == savedData.password) {
                userPreferencesRepository.setLoggedIn(true)
                _loginState.value = LoginStatus.SUCCESS
            } else {
                _loginState.value = LoginStatus.ERROR
            }
        }
    }

    /**
     * Guarda los nuevos datos del usuario y lo redirige al Login.
     */
    fun register() {
        viewModelScope.launch {
            if (_firstName.value.isNotBlank() && _lastName.value.isNotBlank() && _email.value.isNotBlank() && _password.value.isNotBlank()) {
                userPreferencesRepository.saveUserData(
                    firstName = _firstName.value,
                    lastName = _lastName.value,
                    email = _email.value,
                    pass = _password.value
                )
                // Asegura que la sesión no se inicie automáticamente
                userPreferencesRepository.setLoggedIn(false)
                // Notifica a la UI que el registro se completó para volver al Login
                _registrationComplete.value = true
            }
        }
    }

    /**
     * Resetea el estado de registro para evitar navegaciones no deseadas.
     */
    fun onRegistrationHandled() {
        _registrationComplete.value = false
    }

    /**
     * Resetea el estado de login para limpiar mensajes de error.
     */
    fun resetLoginStatus() {
        _loginState.value = LoginStatus.IDLE
    }
}

enum class LoginStatus {
    IDLE, SUCCESS, ERROR
}