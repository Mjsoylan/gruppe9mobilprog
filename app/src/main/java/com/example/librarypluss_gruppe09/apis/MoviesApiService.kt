package com.example.librarypluss_gruppe09.apis

import com.example.librarypluss_gruppe09.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesApiService {
    @GET("search/movie")
    fun searchMovies(
        @Header("Authorization") authorization: String,
        @Query("query") query: String
    ): Call<MovieResponse>
}
