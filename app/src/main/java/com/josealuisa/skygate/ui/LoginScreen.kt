package com.josealuisa.skygate.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josealuisa.skygate.ui.theme.SkyGateTheme

// Datos de acceso correctos (los pide la tarea, fijos en el código)
private const val CORREO_CORRECTO = "jose@skygate.com"
private const val CLAVE_CORRECTA = "Despegue2026"
private const val NOMBRE_USUARIO = "José Enrique Aluisa"

@Composable
fun LoginScreen(onLoginCorrecto: (String) -> Unit) {

    // Estado: lo que escribe el usuario y si se ve la contraseña
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var mostrarClave by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp)
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {

        // Logo
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Flight,
                contentDescription = "Logo SkyGate",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(14.dp)
            )
        }

        // Títulos
        Text(
            text = "Embarque de pasajeros",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Identifícate para continuar",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Campo correo
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo contraseña con ojo
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (mostrarClave) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { mostrarClave = !mostrarClave }) {
                    Icon(
                        imageVector = if (mostrarClave) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = "Mostrar u ocultar contraseña"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error con animación (extra)
        AnimatedVisibility(
            visible = mensajeError != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = mensajeError ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Botón Entrar
        Button(
            onClick = {
                mensajeError = when {
                    correo.isBlank() || clave.isBlank() ->
                        "Rellena el correo y la contraseña."
                    !correo.contains("@") ->
                        "El correo no tiene un formato válido."
                    correo != CORREO_CORRECTO || clave != CLAVE_CORRECTA ->
                        "Usuario o contraseña incorrectos."
                    else -> null
                }
                if (mensajeError == null) {
                    onLoginCorrecto(NOMBRE_USUARIO)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Entrar",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SkyGateTheme {
        LoginScreen(onLoginCorrecto = {})
    }
}