package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.models.Media
import com.google.android.gms.tasks.Task

import kotlinx.coroutines.flow.Flow

interface LibraryService {
    val mediacollection: Flow<List<Media>>
    suspend fun getMedia(mediaId: String): Media?

    suspend fun deleteMedia(mediaId: Media): Task<Void>

    suspend fun addMediaToLibrary(mediaId: Media): String

    suspend fun savemedia(mediaId: Media): String

}
