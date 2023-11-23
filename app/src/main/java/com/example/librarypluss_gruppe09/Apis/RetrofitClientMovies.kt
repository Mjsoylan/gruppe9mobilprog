package com.example.librarypluss_gruppe09.Apis

import com.example.librarypluss_gruppe09.Apis.OkHttpClient
import com.example.librarypluss_gruppe09.Apis.ToStringConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitMovies: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .client(OkHttpClient)
    .addConverterFactory(ToStringConverterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()



