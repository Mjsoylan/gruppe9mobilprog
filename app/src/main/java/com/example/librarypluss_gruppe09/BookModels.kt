package com.example.librarypluss_gruppe09
data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Book>,
    val pageCount: Int,
    val categories: List<String>,
)

data class Book(
    val volumeInfo: BookInfo
)

data class BookInfo(
    val id: String,
    val title: String,
    val authors: List<String>,
    val pageCount: Int,
    val categories: List<String>,
    val imageLinks: ImageLinks?
)
data class ImageLinks(
    val smallThumbnail: String?
)
