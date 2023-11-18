package com.example.librarypluss_gruppe09
data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    // Add other movie attributes you need
)
