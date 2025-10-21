package com.panamericana.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserData(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository(private val context: Context) {

    private val firstNameKey = stringPreferencesKey("user_first_name")
    private val lastNameKey = stringPreferencesKey("user_last_name")
    private val emailKey = stringPreferencesKey("user_email")
    private val passwordKey = stringPreferencesKey("user_password")
    // NUEVA CLAVE: Para saber si la sesión está activa o no
    private val isLoggedInKey = booleanPreferencesKey("is_logged_in")

    suspend fun saveUserData(firstName: String, lastName: String, email: String, pass: String) {
        context.dataStore.edit { preferences ->
            preferences[firstNameKey] = firstName
            preferences[lastNameKey] = lastName
            preferences[emailKey] = email
            preferences[passwordKey] = pass
        }
    }

    val userData: Flow<UserData> = context.dataStore.data.map { preferences ->
        UserData(
            firstName = preferences[firstNameKey] ?: "",
            lastName = preferences[lastNameKey] ?: "",
            email = preferences[emailKey] ?: "",
            password = preferences[passwordKey] ?: ""
        )
    }

    // NUEVO FLUJO: Nos dice si la sesión está activa
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[isLoggedInKey] ?: false
    }

    // NUEVA FUNCIÓN: Marca la sesión como iniciada
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[isLoggedInKey] = isLoggedIn
        }
    }
}