package com.example.librarypluss_gruppe09

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GamesApiService {
    @Headers("Client-ID: 35nfm0jkloxrrfi54afigm9qklpuhq", "Authorization:bearer 2cz8jk3istcu7y6ingfwnh7529lfed")
    @POST("games")
    fun searchGames(@Body query: GameQuery): Call<GameResponse>

}

data class GameQuery(
    val search: String,
    val fields: String
    // ...
)
