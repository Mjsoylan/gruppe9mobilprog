package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.models.Media
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface GoalsService {

    val goalscollection: Flow<List<Media>>

    suspend fun getGoal(mediaId: String): Media?

    suspend fun deleteGoal(mediaId: String): Task<Void>

    suspend fun createGoal(mediaId: String) : String

    suspend fun addMediaToLibrary(mediaId: Media): String
}