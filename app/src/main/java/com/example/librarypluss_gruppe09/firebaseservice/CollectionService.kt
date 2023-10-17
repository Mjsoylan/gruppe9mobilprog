package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.models.Media
import kotlinx.coroutines.flow.Flow

interface CollectionService {
    val mediacollection: Flow<List<Media>>
    suspend fun getMedia(mediaId: String): Media?
    suspend fun saveMedia(media: Media): String
}