package com.josealuisa.skygate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.josealuisa.skygate.ui.LoginScreen
import com.josealuisa.skygate.ui.WelcomeScreen
import com.josealuisa.skygate.ui.theme.SkyGateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyGateTheme {
                // Surface pinta el fondo con el color del tema (claro u oscuro)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SkyGateApp()
                }
            }
        }
    }
}

@Composable
fun SkyGateApp() {
    // null = nadie ha iniciado sesión; con texto = nombre del usuario
    var usuario by remember { mutableStateOf<String?>(null) }

    if (usuario == null) {
        LoginScreen(onLoginCorrecto = { nombre -> usuario = nombre })
    } else {
        WelcomeScreen(
            nombre = usuario!!,
            onCerrarSesion = { usuario = null }
        )
    }
}