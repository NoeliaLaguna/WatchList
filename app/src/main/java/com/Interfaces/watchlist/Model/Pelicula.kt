package com.Interfaces.watchlist.Model

data class Pelicula(
    val id: Int,
    val titulo: String,
    val genero: String,
    val año: Int,
    val puntuacion: Double,
    val vista: Boolean,
    val imagen: Int
)