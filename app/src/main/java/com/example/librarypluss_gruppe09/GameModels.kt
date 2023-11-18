package com.example.librarypluss_gruppe09

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    @SerializedName("name") val name: String?
)

data class Game(
    val id: Int,
    val name: String
)
