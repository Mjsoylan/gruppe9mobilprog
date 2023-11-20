package com.example.librarypluss_gruppe09

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("volumes")
    fun searchMovies(@Query("q") query: String): Call<MovieResponse>
}

