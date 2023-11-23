package com.example.librarypluss_gruppe09.models

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    @SerializedName("name") val name: String?,
    //@SerializedName("genres")val genres: List<Int>,
    //@SerializedName("involved_companies")val involved_companies: List<String>
)