package com.example.librarypluss_gruppe09.models


data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String
)