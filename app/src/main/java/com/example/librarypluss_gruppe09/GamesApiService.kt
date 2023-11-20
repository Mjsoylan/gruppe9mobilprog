package com.example.librarypluss_gruppe09

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GamesApiService {
    @POST("games")
    fun searchGames(
        @Header("Client-ID") clientID: String,
        @Header("Authorization") authorization: String,
        @Body body: String
    ): Call<List<GameResponse>>
}
