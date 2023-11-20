package com.example.librarypluss_gruppe09

import com.example.librarypluss_gruppe09.models.Media

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String
)