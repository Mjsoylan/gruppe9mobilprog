package com.example.librarypluss_gruppe09

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitMovies: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .client(OkHttpClient)
    .addConverterFactory(ToStringConverterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()



