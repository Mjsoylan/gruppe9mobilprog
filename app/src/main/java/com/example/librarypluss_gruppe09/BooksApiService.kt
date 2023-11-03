package com.example.librarypluss_gruppe09

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    fun searchBooks(@Query("q") query: String): Call<BookResponse>
}

