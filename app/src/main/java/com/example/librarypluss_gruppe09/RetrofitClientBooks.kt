package com.example.librarypluss_gruppe09

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientBooks {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val instance: BooksApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BooksApiService::class.java)


    }
}
