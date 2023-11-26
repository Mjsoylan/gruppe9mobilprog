package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.models.Feedmedia
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.UserAccunt
import com.google.android.gms.tasks.Task

import kotlinx.coroutines.flow.Flow

interface LibraryService {
    val mediacollection: Flow<List<Media>>
    val feedcollection: Flow<List<Feedmedia>>
    val UsersCollection: Flow<List<UserAccunt>>
    val MEDIA_COLLECTION: String
    val GOALS_COLLECTION: String
    val USER_COOLECTION: String
    val FEED_COLLECTION: String
    val GOAL_MEDIA_COLLECTION: String

    suspend fun getMedia(mediaId: String): Media?
    suspend fun getFeedMedia(mediaId: String): Media?
    suspend fun getGoalMedia(mediaId: String): Media?



    suspend fun deleteMedia(mediaId: Media): Task<Void>

    suspend fun addMediaToLibrary(mediaId: Media): String

    suspend fun savemedia(mediaId: Media): String

}
