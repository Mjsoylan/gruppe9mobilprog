package com.example.librarypluss_gruppe09

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    @SerializedName("name") val name: String?,
    //@SerializedName("genres")val genres: List<Int>,
    //@SerializedName("involved_companies")val involved_companies: List<String>
)

data class Game(
    val id: Int,
    val name: String,
    //val genres: List<Int>,
    //val involved_companies: List<String>
)
