package com.panamericana.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.panamericana.app.navigation.AppNavigation
import com.panamericana.app.ui.theme.PanamericanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanamericanaTheme {
                AppNavigation()
            }
        }
    }
}