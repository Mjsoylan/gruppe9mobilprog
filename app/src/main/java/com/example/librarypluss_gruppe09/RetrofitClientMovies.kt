package com.example.librarypluss_gruppe09

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientMovies {
    private const val BASE_URL = "https://api.igdb.com/v4/search/"

    val instance: MoviesApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MoviesApiService::class.java)


    }
}
