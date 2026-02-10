package com.Interfaces.watchlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListaPeliculas(
    navcontroller: NavController
) {
    val peliculas = listOf(
        Pelicula(1, "Inception", "Ciencia ficción", 2010, 8.8, true, true),
        Pelicula(2, "Interstellar", "Ciencia ficción", 2014, 8.6, false, true),
        Pelicula(3, "La Vida es Bella", "Bélico/Comedia", 1997, 9.7, true, true),
        Pelicula(4, "Parasite", "Thriller", 2019, 8.6, false, false),
        Pelicula(5, "La La Land", "Romance", 2016, 8.0, false, false),
        Pelicula(6, "Breaking Bad", "Drama", 2008, 9.5, false, false),
        Pelicula(7, "Juego de Tronos", "Drama", 2011, 9.2, true, false),
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("WatchList") }
            )
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    //Añadir película
                    navcontroller.navigate("inicio") // luego cambiará a "add"
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
                PeliculaItem(peliculaI = pelicula)
            }

        }
    }
}

@Composable
fun PeliculaItem(
    peliculaI: Pelicula

) {
    var checked by remember { mutableStateOf(peliculaI.vista) }
    var isToggled by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //TARJETA DE LA PELÍCULA
        OutlinedCard(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // CHECKBOX A LA IZQUIERDA
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
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
                }
            }
        }

        //ICONO TOGGLE (BORRAR PELI) A LA DERECHA (FUERA DE LA TARJETA)
        IconButton(
            onClick = { isToggled = !isToggled }
        ) {
            Icon(
                imageVector = if (isToggled)
                    Icons.Filled.Delete
                else
                    Icons.Outlined.Delete,
                contentDescription = if (isToggled)
                    "Eliminar seleccionado"
                else
                    "Eliminar no seleccionado"
            )
        }
        /*
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Outlined.Image, contentDescription = null)
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
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
                    }
                }

         */
    }
}




