package com.example.librarypluss_gruppe09

import retrofit2.Call
class BooksRepository {

    fun searchBooks(query: String): Call<BookResponse> {
        return RetrofitClient.instance.searchBooks(query)
    }
}
