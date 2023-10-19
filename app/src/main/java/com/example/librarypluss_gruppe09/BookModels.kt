package com.example.librarypluss_gruppe09
data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Book>
)

data class Book(
    val volumeInfo: BookInfo
)

data class BookInfo(
    val id: String,
    val title: String,
    val authors: List<String>
)
