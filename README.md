# SkyGate – App de acceso (Login y Bienvenida)

App Android para una PDA de embarque de pasajeros, hecha con Kotlin,
Jetpack Compose y Material Design 3 (minSdk 26).

## Credenciales de prueba
- Correo: jose@skygate.com
- Contraseña: Despegue2026
- Nombre mostrado en la bienvenida: José Enrique Aluisa

## Diseño en Figma
https://www.figma.com/design/5lSY8WeGh999cwDC6HKf7x/Skygate?node-id=1-3&t=VqbebhBKRlKjtV38-1

## Decisiones de diseño
- Tema aeropuerto: azul (confianza, aerolíneas) y naranja (balizas y chalecos de pista).
- Colores como variables en Figma (modos Light y Dark) con los mismos nombres que en Color.kt.
- 4 estilos de texto (Headline, Title, Body, Label) pasados a Type.kt.
- Ilustración de bienvenida con Canvas (sol, nubes, estelas) y un icono de avión.

## Extras implementados
1. Modo oscuro coherente con la marca.
2. Validación avanzada: campos vacíos y formato de correo.
3. Animación del mensaje de error (AnimatedVisibility).

## Estructura
- ui/theme: Color.kt, Type.kt, Theme.kt
- ui: LoginScreen.kt, WelcomeScreen.kt
- MainActivity.kt: decide qué pantalla se muestra según el estado.