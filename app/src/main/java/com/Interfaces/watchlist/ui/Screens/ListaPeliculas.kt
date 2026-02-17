package com.Interfaces.watchlist.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.material.icons.filled.Done
import com.Interfaces.watchlist.Model.Pelicula
import com.Interfaces.watchlist.R


/**
 * Pantalla principal que muestra la lista de películas.
 *
 * Funcionalidad:
 *      - Muestra todas las películas almacenadas.
 *      - Permite filtrar por: Todos / Vistos / Pendientes.
 *      - Permite marcar una película como vista o no vista..
 *      - Permite eliminar una película con diálogo de confirmación.
 *      - Incluye botón flotante para navegar a la pantalla de añadir.
 *
 *  @param navcontroller Controlador de navegación.
 *  @param peliculas Lista mutable de películas que se muestra y actualiza dinámicamente.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListaPeliculas(
    navcontroller: NavController,
    peliculas: MutableList<Pelicula>
) {

    var peliculaAEliminar by remember { mutableStateOf<Pelicula?>(null) }

    var filtroSeleccionado by remember { mutableStateOf("todos") }

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

                        Spacer(modifier = Modifier.width(8.dp))

                    }
                }
            },

            floatingActionButton = {
                SmallFloatingActionButton(
                    onClick = {

                        navcontroller.navigate("add")
                    },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Añadir película"
                    )
                }
            }

        ) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                val numeroPendientes = peliculas.count { !it.vista }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    FiltroButton(
                        texto = "Todos",
                        seleccionado = filtroSeleccionado == "todos"
                    ) {
                        filtroSeleccionado = "todos"
                    }

                    FiltroButton(
                        texto = "Vistos",
                        seleccionado = filtroSeleccionado == "vistos"
                    ) {
                        filtroSeleccionado = "vistos"
                    }
                    FiltroButton(
                        texto = "Pendientes",
                        seleccionado = filtroSeleccionado == "pendientes",
                        contador = numeroPendientes
                    ) {
                        filtroSeleccionado = "pendientes"
                    }

                }
                val peliculasFiltradas = when (filtroSeleccionado) {
                    "vistos" -> peliculas.filter { it.vista }
                    "pendientes" -> peliculas.filter { !it.vista }
                    else -> peliculas
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(peliculasFiltradas) { pelicula ->
                        PeliculaItem(
                            peliculaI = pelicula,
                            onDeleteClick = { peliculaAEliminar = pelicula },
                            onToggleVista = { peli ->
                                val index = peliculas.indexOf(peli)
                                peliculas[index] = peli.copy(vista = !peli.vista)
                            }

                        )
                    }
                }
            }
        }

        if (peliculaAEliminar != null) {
            AlertDialog(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = null
                    )
                },
                title = {
                    Text("Eliminar película")
                },
                text = {
                    Text("¿Seguro que quieres eliminar \"${peliculaAEliminar!!.titulo}\"?")
                },
                onDismissRequest = {
                    peliculaAEliminar = null
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            peliculas.remove(peliculaAEliminar)
                            peliculaAEliminar = null
                        }
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            peliculaAEliminar = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}



/**
 * Composable que representa una tarjeta individual de película.
 *
 * Funcionalidad:
 *      - Muestra la imagen, título, género, año y puntuación.
 *      - Permite cambiar el estado de vista (visto / no visto).
 *      - Permite eliminar la película mediante el callback.
 *
 * @param peliculaI -> Película que se representa.
 * @param onDeleteClick -> Acción que se ejecuta al pulsar eliminar.
 * @param onToggleVista -> Acción que alterna el estado de vista.
 */

@Composable
fun PeliculaItem(
    peliculaI: Pelicula,
    onDeleteClick: () -> Unit,
    onToggleVista: (Pelicula) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedCard(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
            ),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        ) {

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Image(
                    painter = painterResource(id = peliculaI.imagen),
                    contentDescription = peliculaI.titulo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .width(95.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))


                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        text = peliculaI.titulo,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "${peliculaI.genero} · ${peliculaI.año}",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "⭐ ${peliculaI.puntuacion}",
                        style = MaterialTheme.typography.bodySmall
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            IconButton(
                                onClick = { onToggleVista(peliculaI) },
                                modifier = Modifier.size(110.dp)
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = if (peliculaI.vista)
                                            R.drawable.visto
                                        else
                                            R.drawable.novisto
                                    ),
                                    contentDescription = "Estado visto",
                                    modifier = Modifier.size(100.dp)
                                )
                            }

                            IconButton(
                                onClick = onDeleteClick,
                                modifier = Modifier.size(110.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.eliminar),
                                    contentDescription = "Eliminar",
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



/**
 * Botón de filtro
 *
 * Funcionalidad:
 *      - Permite seleccionar el tipo de filtro de la lista.
 *      - Muestra un icono cuando está seleccionado.
 *
 * @param texto -> Texto que muestra el botón.
 * @param seleccionado -> Indica si el filtro está activo.
 * @param onClick -> Acción que se ejecuta al pulsar el botón.
 */
@Composable
fun FiltroButton(
    texto: String,
    seleccionado: Boolean,
    contador: Int? = null,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },

        icon = {
            if (seleccionado) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null
                )
            }
        },
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(texto)

                if (contador != null && contador > 0) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Red,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = contador.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    )
}




