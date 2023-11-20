package com.example.librarypluss_gruppe09

import retrofit2.Call
class MoviesRepository {

    fun searchMovies(query: String): Call<MovieResponse> {
        return RetrofitClientMovies.instance.searchMovies(query)
    }
}
