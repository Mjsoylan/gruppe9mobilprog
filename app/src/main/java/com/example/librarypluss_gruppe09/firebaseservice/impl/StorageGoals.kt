package com.example.librarypluss_gruppe09.firebaseservice.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.librarypluss_gruppe09.firebaseservice.GoalsService
import com.example.librarypluss_gruppe09.models.History
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageGoals
@Inject
constructor(private val firestore: FirebaseFirestore) : GoalsService {

    //Media
    override val mediascollection: Flow<List<Media>>
        get() = firestore.collection(MEDIA_COLLECTION).dataObjects()


    //Settgoal
    override val settgoalscollection: Flow<List<SettDescriptionGoal>>
        get() = firestore.collection(SETT_GOALS).dataObjects()


    //History
    override val historycollection: Flow<List<History>>
        get() = firestore.collection(HISTORY_GOALS_DESCRIPTIO).dataObjects()


    override suspend fun addGoal(mediaId: SettDescriptionGoal): String =
//        val mediaUserId = mediaId.copy(userId = curr)
        firestore.collection(HISTORY_GOALS_DESCRIPTIO).add(mediaId).await().id

    override suspend fun getGoal(mediaId: String): SettDescriptionGoal? =
        firestore.collection(SETT_GOALS).document(mediaId).get().await().toObject()


    // https://firebase.google.com/docs/firestore/manage-data/add-data#kotlin+ktx
    override suspend fun updategoal(goalId: SettDescriptionGoal, updateFild: String): Task<Void> =
        firestore.collection(SETT_GOALS).document(goalId.goalId).update(
            "description", updateFild
        ).addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }


    override suspend fun sendMediaToHistory(mediaId: Media): String =
        firestore.collection(HISTORY_GOALS_DESCRIPTIO).add(mediaId).await().id


    override suspend fun sendGoalToHistory(goalId: SettDescriptionGoal): String =
        firestore.collection(SETT_GOALS).add(goalId).await().id


    override suspend fun deleteGoal(goalId: SettDescriptionGoal): Task<Void> =
        firestore.collection(SETT_GOALS).document(goalId.goalId).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

    override suspend fun deleteMedia(media: Media): Task<Void> =
        firestore.collection(MEDIA_COLLECTION).document(media.mediaId).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }


    override suspend fun createGoal(mediaId: SettDescriptionGoal): String =
        firestore.collection(SETT_GOALS).add(mediaId).await().id


    override suspend fun savemedia(mediaId: Media): String =
//        val mediaUserId = mediaId.copy(userId = auth.currentUserId)
        firestore.collection(MEDIA_COLLECTION).add(mediaId).await().id


    companion object {
        private const val MEDIA_COLLECTION = "media"

        private const val SETT_GOALS = "settgoal"

        private const val HISTORY_GOALS_DESCRIPTIO = "history"

    }
}

