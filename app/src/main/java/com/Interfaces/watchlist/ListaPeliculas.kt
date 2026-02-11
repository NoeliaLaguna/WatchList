package com.Interfaces.watchlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListaPeliculas(
    navcontroller: NavController,
    peliculas: MutableList<Pelicula>
) {
    //Prueba

    var peliculaAEliminar by remember { mutableStateOf<Pelicula?>(null) }

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
                        //Añadir película
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
            LazyColumn(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(peliculas) { pelicula ->
                    PeliculaItem(
                        peliculaI = pelicula,
                        onDeleteClick = {
                            peliculaAEliminar = pelicula
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

@Composable
fun PeliculaItem(
    peliculaI: Pelicula,
    onDeleteClick: () -> Unit
) {
    var checked by remember { mutableStateOf(peliculaI.vista) }

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
                Icon(Icons.Outlined.Image, contentDescription = null)
            }

                // 🔹 IMAGEN
                Image(
                    painter = painterResource(id = peliculaI.imagen),
                    contentDescription = peliculaI.titulo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .width(95.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                // 🔹 CONTENIDO
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

                    // 🔹 ACCIONES MÁS PEGADAS
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            IconButton(
                                onClick = onDeleteClick,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            ) {

                                // 🔹 IMAGEN DINÁMICA VISTO / NO VISTO
                                Image(
                                    painter = painterResource(
                                        id = if (checked) R.drawable.visto else R.drawable.novisto
                                    ),
                                    contentDescription = "Estado visto",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clickable {
                                            checked = !checked
                                        }
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Inside,

                                    )
                            }

                            IconButton(
                                onClick = onDeleteClick,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.eliminar),
                                    contentScale = ContentScale.Inside,
                                    contentDescription = "Eliminar película",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





