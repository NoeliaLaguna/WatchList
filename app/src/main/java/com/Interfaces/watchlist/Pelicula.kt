package com.Interfaces.watchlist

data class Pelicula(
    val id: Int,
    val titulo: String,
    val genero: String,
    val año: Int,
    val puntuacion: Double,
    val favorita: Boolean,
    val vista: Boolean
)
