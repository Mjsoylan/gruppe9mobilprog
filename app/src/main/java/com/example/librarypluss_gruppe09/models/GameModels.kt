package com.example.librarypluss_gruppe09.models

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    @SerializedName("name") val name: String?
)