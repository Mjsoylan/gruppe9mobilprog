package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.models.History
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface GoalsService {

    //Media
    val mediascollection: Flow<List<Media>>

    suspend fun savemedia(mediaId: Media): String


    //settgoal
    val settgoalscollection: Flow<List<SettDescriptionGoal>>

    //history
    val historycollection: Flow<List<History>>

    suspend fun sendMediaToHistory(mediaId: History): String
    suspend fun sendGoalToHistory(goalId: History): String


    suspend fun getGoal(mediaId: String): SettDescriptionGoal?

    suspend fun updategoal(goalId: SettDescriptionGoal, updateFild: String): Task<Void>


    suspend fun deleteMedia(mediaId: Media): Task<Void>

    suspend fun deleteGoal(goalId: SettDescriptionGoal): Task<Void>


    suspend fun createGoal(goalId: SettDescriptionGoal): String


    suspend fun addGoal(mediaId: SettDescriptionGoal): String

}