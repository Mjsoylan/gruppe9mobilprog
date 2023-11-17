package com.example.librarypluss_gruppe09

import retrofit2.Call
class BooksRepository {

    fun searchBooks(query: String): Call<BookResponse> {
        return RetrofitClientBooks.instance.searchBooks(query)
    }
}
