package com.example.librarypluss_gruppe09

import com.example.librarypluss_gruppe09.Apis.RetrofitClientBooks
import com.example.librarypluss_gruppe09.models.BookResponse
import retrofit2.Call
class BooksRepository {

    fun searchBooks(query: String): Call<BookResponse> {
        return RetrofitClientBooks.instance.searchBooks(query)
    }
}
