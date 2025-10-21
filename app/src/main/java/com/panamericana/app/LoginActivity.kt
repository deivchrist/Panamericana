package com.panamericana.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.panamericana.app.ui.theme.PanamericanaTheme
import androidx.lifecycle.lifecycleScope
import com.panamericana.app.data.AppDatabase
import com.panamericana.app.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Si ya hay sesión guardada, ir a MainActivity
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        val savedEmail = prefs.getString("email", null)
        if (savedEmail != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        // Seed a test user if missing and set content
        val db = AppDatabase.getInstance(applicationContext)
        lifecycleScope.launch {
            // insert a test user (ignored if exists) en IO
            val existing = withContext(Dispatchers.IO) { db.userDao().findByEmail("bonifacio") }
            if (existing == null) {
                withContext(Dispatchers.IO) {
                    db.userDao().insert(User(email = "bonifacio", password = "zevillano123"))
                }
            }
        }

        setContent {
            PanamericanaTheme {
                LoginScreen(db) { email ->
                    // Guardar sesión y navegar a MainActivity
                        prefs.edit().putString("email", email).apply()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(db: AppDatabase, onLoginSuccess: (String) -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    // Paleta basada en la imagen
    val bgRed = Color(0xFFDE4636)
    val cardBlue = Color(0xFF123248)
    val fieldBg = Color(0xFF0F2B36).copy(alpha = 0.9f)
    val accent = Color(0xFFFF8A65)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(bgRed),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo (se usa drawable existente `logo` si está disponible)
            val ctx = LocalContext.current
            val logoId = ctx.resources.getIdentifier("logo", "drawable", ctx.packageName)
            if (logoId != 0) {
                Image(painter = painterResource(id = logoId), contentDescription = "logo", modifier = Modifier.size(120.dp))
                Spacer(modifier = Modifier.size(12.dp))
            }

            Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.width(340.dp)) {
                Column(modifier = Modifier
                    .background(cardBlue)
                    .padding(20.dp)
                ) {
                    Text(text = "Usuario", color = Color(0xFFB0C4C9))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(fieldBg, RoundedCornerShape(12.dp))
                            .padding(top = 8.dp, bottom = 12.dp)
                    )

                    Text(text = "Contraseña", color = Color(0xFFB0C4C9))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(fieldBg, RoundedCornerShape(12.dp))
                            .padding(top = 8.dp, bottom = 16.dp)
                    )

                    Button(onClick = {
                        errorText = ""
                        if (email.isBlank() || password.isBlank()) {
                            errorText = "Por favor completa email y contraseña"
                        } else {
                            coroutineScope.launch {
                                val user = withContext(Dispatchers.IO) { db.userDao().findByEmail(email) }
                                if (user == null) {
                                    withContext(Dispatchers.Main) {
                                        errorText = "Usuario o contraseña incorrectos"
                                    }
                                } else if (user.password != password) {
                                    withContext(Dispatchers.Main) {
                                        errorText = "Usuario o contraseña incorrectos"
                                    }
                                } else {
                                    withContext(Dispatchers.Main) {
                                        onLoginSuccess(email)
                                    }
                                }
                            }
                        }
                    }, shape = RoundedCornerShape(24.dp), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)) {
                        Text(text = "Ingresar")
                    }

                    if (errorText.isNotEmpty()) {
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = errorText, color = accent)
                    }
                }
            }
        }
    }
}
