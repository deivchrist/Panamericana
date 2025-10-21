package com.panamericana.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.panamericana.app.ui.viewmodel.AuthViewModel
import com.panamericana.app.ui.viewmodel.LoginStatus

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    val firstName by authViewModel.firstName.collectAsState()
    val lastName by authViewModel.lastName.collectAsState()
    val email by authViewModel.email.collectAsState()
    val password by authViewModel.password.collectAsState()
    val loginState by authViewModel.loginState.collectAsState()
    val registrationComplete by authViewModel.registrationComplete.collectAsState()

    LaunchedEffect(registrationComplete) {
        if (registrationComplete) {
            navController.popBackStack()
            authViewModel.onRegistrationHandled()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        // Nuevos campos de texto
        OutlinedTextField(value = firstName, onValueChange = { authViewModel.onFirstNameChange(it) }, label = { Text("Nombres") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = lastName, onValueChange = { authViewModel.onLastNameChange(it) }, label = { Text("Apellidos") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = { authViewModel.onEmailChange(it) }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = password, onValueChange = { authViewModel.onPasswordChange(it) }, label = { Text("Contraseña") })
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { authViewModel.register() }) {
            Text("Registrarse")
        }
        TextButton(onClick = { navController.popBackStack() }) {
            Text("¿Ya tienes cuenta? Inicia Sesión")
        }
    }
}