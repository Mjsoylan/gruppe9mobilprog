package com.example.librarypluss_gruppe09.Apis

import com.example.librarypluss_gruppe09.models.GameResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GamesApiService {
    @POST("games")
    fun searchGames(
        @Header("Client-ID") clientID: String,
        @Header("Authorization") authorization: String,
        @Body body: String
    ): Call<List<GameResponse>>
}
