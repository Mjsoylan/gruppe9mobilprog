package com.example.librarypluss_gruppe09.firebaseservice;

import com.example.librarypluss_gruppe09.models.Media;

import kotlinx.coroutines.flow.Flow;

interface LibraryService {
    val mediacollection:Flow<List<Media>>
    suspend fun getMedia(mediaId:String):Media?
}
