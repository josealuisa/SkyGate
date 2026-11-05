package com.josealuisa.skygate.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josealuisa.skygate.ui.theme.SkyGateTheme

@Composable
fun WelcomeScreen(
    nombre: String,            // DATO: llega desde fuera, no está escrito a mano aquí
    onCerrarSesion: () -> Unit // ACCIÓN: función que se ejecuta al pulsar el botón
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Cabecera: logo + saludo + nombre recibido por parámetro
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(56.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.Flight,
                            contentDescription = "Logo SkyGate",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Text(
                    text = "¡Listo para embarcar!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = nombre, // el nombre que nos han enviado
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            Ilustracion()

            // Botón de cerrar sesión, abajo y centrado
            OutlinedButton(
                onClick = onCerrarSesion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Cerrar sesión", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

// Ilustración: sol, nubes y estelas con Canvas + icono de avión encima.
@Composable
fun Ilustracion() {
    val colorSol = MaterialTheme.colorScheme.secondary
    val colorNube = MaterialTheme.colorScheme.primaryContainer

    Box(modifier = Modifier.size(width = 234.dp, height = 240.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(color = colorSol, radius = 32.dp.toPx(), center = Offset(182.dp.toPx(), 48.dp.toPx()))
            drawOval(color = colorNube, topLeft = Offset(8.dp.toPx(), 70.dp.toPx()), size = Size(90.dp.toPx(), 36.dp.toPx()))
            drawOval(color = colorNube, topLeft = Offset(136.dp.toPx(), 160.dp.toPx()), size = Size(90.dp.toPx(), 36.dp.toPx()))
            drawRoundRect(color = colorNube, topLeft = Offset(30.dp.toPx(), 150.dp.toPx()), size = Size(40.dp.toPx(), 4.dp.toPx()))
            drawRoundRect(color = colorNube, topLeft = Offset(20.dp.toPx(), 164.dp.toPx()), size = Size(28.dp.toPx(), 4.dp.toPx()))
        }
        // Avión: icono de Material, centrado, de 96 dp como en Figma
        Icon(
            imageVector = Icons.Filled.Flight,
            contentDescription = "Avión despegando",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    SkyGateTheme {
        WelcomeScreen(nombre = "José Enrique Aluisa", onCerrarSesion = {})
    }
}