package com.Interfaces.watchlist

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPeliculaScreen(
    navController: NavController,
    onAddPelicula: (Pelicula) -> Unit
) {

    //Prueba

    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var anyo by remember { mutableStateOf("") }
    var puntuacion by remember { mutableStateOf("") }
    var vista by remember { mutableStateOf(false) }
    var favorita by remember { mutableStateOf(false) }




    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔹 IMAGEN DE FONDO
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 🔹 CAPA OSCURA PARA QUE SE LEA BIEN
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.4f))
        )

        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
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
                    style = MaterialTheme.typography.titleLarge
                )


                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = anyo,
                    onValueChange = { anyo = it },
                    label = { Text("Año") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = puntuacion,
                    onValueChange = { puntuacion = it },
                    label = { Text("Puntuación") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Vista")
                    Switch(checked = vista, onCheckedChange = { vista = it })
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Favorita")
                    Switch(checked = favorita, onCheckedChange = { favorita = it })
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        val nuevaPelicula = Pelicula(
                            id = (0..100000).random(), // mock
                            titulo = titulo,
                            genero = genero,
                            año = anyo.toIntOrNull() ?: 2024,
                            puntuacion = puntuacion.toDoubleOrNull() ?: 0.0,
                            favorita = favorita,
                            vista = vista,
                            imagen = R.drawable.pordefecto
                        )

                        onAddPelicula(nuevaPelicula)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar película")
                }

            }
        }
    }
}
