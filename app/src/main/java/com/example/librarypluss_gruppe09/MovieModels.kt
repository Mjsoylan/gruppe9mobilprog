package com.example.librarypluss_gruppe09
data class MovieResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Movie>
)

data class Movie(
    val volumeInfo: MovieInfo
)

data class MovieInfo(
    val id: String,
    val title: String,
    val authors: List<String>
)
