package com.example.librarypluss_gruppe09.firebaseservice.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.librarypluss_gruppe09.firebaseservice.GoalsService
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

    override val goalscollection: Flow<List<Media>>
        get() = firestore.collection(GOALS_COLLECTION).dataObjects()

    override val settgoalscollection: Flow<List<SettDescriptionGoal>>
        get() = firestore.collection(SETT_GOALS_DESCRIPTIO).dataObjects()

    //todo add a flow witch !Described goals added from floating button
    override suspend fun getGoal(mediaId: String): Media? =
        firestore.collection(GOALS_COLLECTION).document(mediaId).get().await().toObject()

    //    todo ########## Marius ##########
    override suspend fun sendMediaToLibrary(mediaId: Media): String =
        firestore.collection(MEDIA_COLLECTION).add(mediaId).await().id

    override suspend fun deleteGoal(mediaId: Media): Task<Void> =
        firestore.collection(GOALS_COLLECTION).document(mediaId.mediaId).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

    //todo implement in AddScreen class
    override suspend fun createGoal(mediaId: String): String =
        firestore.collection(GOALS_COLLECTION).add(mediaId).await().id


    //todo querry based on the user id as a key to get det addetmedia to media collection
    companion object {
        private const val MEDIA_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/addedMedia"

        //        private const val MEDIA_COLLECTION = "media"
        private const val GOALS_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/goals"

        private const val SETT_GOALS_DESCRIPTIO = "user/gRGLI4BDi9QvCVjT5OaI/settGoals"

    }
}

