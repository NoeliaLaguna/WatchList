package com.Interfaces.watchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Interfaces.watchlist.Model.Pelicula
import com.Interfaces.watchlist.ui.Screens.AddPeliculaScreen
import com.Interfaces.watchlist.ui.Screens.ListaPeliculas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navegacion()
        }
    }


    /**
     * Configuración principal de navegación de la aplicación.
     *
     * Define las rutas:
     *      - "inicio" → Lista de películas.
     *      - "add" → Pantalla para añadir nueva película.
     *
     * Mantiene la lista principal de películas en memoria usando mutableStateListOf,
     * lo que permite que la UI se actualice automáticamente ante cambios.
     */
    @Composable
    fun Navegacion() {

        val navController = rememberNavController()

        val peliculas = remember {
            mutableStateListOf(
                Pelicula(1, "Inception", "Ciencia ficción", 2010, 8.8, true, R.drawable.inception),
                Pelicula(
                    2,
                    "Interstellar",
                    "Ciencia ficción",
                    2014,
                    8.6,
                    false,
                    R.drawable.interstellar
                ),
                Pelicula(
                    3,
                    "La Vida es Bella",
                    "Bélico/Comedia",
                    1997,
                    9.7,
                    true,
                    R.drawable.lavidaesbella
                ),
                Pelicula(4, "Parasite", "Thriller", 2019, 8.6, false, R.drawable.parasite),
                Pelicula(5, "La La Land", "Romance", 2016, 8.0, false, R.drawable.lalaland),
            )
        }

        NavHost(navController = navController, startDestination = "inicio") {
            composable("inicio") {
                ListaPeliculas(
                    navController,
                    peliculas
                )
            }
            composable("add") {
                AddPeliculaScreen(navController, onAddPelicula = { nuevaPelicula ->
                    peliculas.add(nuevaPelicula)
                })
            }
        }
    }
}
