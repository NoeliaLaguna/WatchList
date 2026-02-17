package com.Interfaces.watchlist.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.Interfaces.watchlist.Model.Pelicula
import com.Interfaces.watchlist.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

/**
 * Pantalla para añadir una nueva película a la WatchList.
 * Funcionalidad:
 *      - Permite introducir título, género, año y puntuación.
 *      - Al pulsar el botón animado (Lottie), crea un nuevo objeto Pelicula.
 *      - Ejecuta el callback onAddPelicula para añadirla a la lista principal.
 *      - Vuelve automáticamente a la pantalla anterior tras terminar la animación.
 *
 * Parámetros:
 *      @param -> navController Controlador de navegación.
 *      @param -> onAddPelicula Callback que recibe la nueva película creada.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPeliculaScreen(
    navController: NavController,
    onAddPelicula: (Pelicula) -> Unit
) {

    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var anyo by remember { mutableStateOf("") }
    var puntuacion by remember { mutableStateOf("") }
    var vista by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.botonguardar)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        iterations = 1
    )

    LaunchedEffect(progress) {
        if (progress == 1f) {
            delay(200)
            navController.popBackStack()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.fondo_topbarr),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {

                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = "Volver",
                                modifier = Modifier.size(45.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Añadir película",
                    style = MaterialTheme.typography.titleLarge,
                    color = White
                )

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = colors(
                        unfocusedContainerColor = White.copy(alpha = 0.85f),
                        focusedContainerColor = White.copy(alpha = 0.95f),
                        cursorColor = MaterialTheme.colorScheme.primary
                    )
                )

                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = colors(
                        unfocusedContainerColor = White.copy(alpha = 0.85f),
                        focusedContainerColor = White.copy(alpha = 0.95f),
                        cursorColor = MaterialTheme.colorScheme.primary
                    )
                )

                OutlinedTextField(
                    value = anyo,
                    onValueChange = { anyo = it },
                    label = { Text("Año") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = colors(
                        unfocusedContainerColor = White.copy(alpha = 0.85f),
                        focusedContainerColor = White.copy(alpha = 0.95f),
                        cursorColor = MaterialTheme.colorScheme.primary
                    )
                )

                OutlinedTextField(
                    value = puntuacion,
                    onValueChange = { puntuacion = it },
                    label = { Text("Puntuación") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = colors(
                        unfocusedContainerColor = White.copy(alpha = 0.85f),
                        focusedContainerColor = White.copy(alpha = 0.95f),
                        cursorColor = MaterialTheme.colorScheme.primary
                    )

                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(enabled = !isPlaying) {
                            isPlaying = true

                            val nuevaPelicula = Pelicula(
                                id = (0..100000).random(),
                                titulo = titulo,
                                genero = genero,
                                año = anyo.toIntOrNull() ?: 2024,
                                puntuacion = puntuacion.toDoubleOrNull() ?: 0.0,
                                vista = vista,
                                imagen = R.drawable.pordefecto
                            )
                            onAddPelicula(nuevaPelicula)
                        }
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.fillMaxSize()
                    )

                    val fuenteGuardar = FontFamily(
                        Font(R.font.fuenteguardar, FontWeight.Normal)
                    )

                    Text(
                        text = "GUARDAR",
                        color = White,
                        fontFamily = fuenteGuardar,
                        fontSize = 27.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
