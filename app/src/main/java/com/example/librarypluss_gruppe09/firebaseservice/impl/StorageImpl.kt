package com.example.librarypluss_gruppe09.firebaseservice.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.librarypluss_gruppe09.firebaseservice.CollectionService
import com.example.librarypluss_gruppe09.models.Media
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : CollectionService {

    override val mediacollection: Flow<List<Media>>
        get() = firestore.collection(MEDIA_COLLECTION).dataObjects()
    override suspend fun getMedia(mediaId: String): Media? =
        firestore.collection(MEDIA_COLLECTION).document(mediaId).get().await().toObject()

  override val goalscollection: Flow<List<Media>>
        get() = firestore.collection(GOALS_COLLECTION).dataObjects()

    override suspend fun getGoal(mediaId: String): Media? =
        firestore.collection(GOALS_COLLECTION).document(mediaId).get().await().toObject()

    override suspend fun addMediaToLibrary(media: Media): String =
         firestore.collection(MEDIA_COLLECTION).add(media).await().id

    override suspend fun deleteGoal(mediaId: String): Task<Void> =
        firestore.collection(MEDIA_COLLECTION).document(mediaId).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

    //todo implement in edd media screen class
    override suspend fun createGoal(mediaId: String): String =
        firestore.collection(GOALS_COLLECTION).add(mediaId).await().id



    //todo querry based on the user id as a key to get det addetmedia to media collection
    companion object {
        private const val MEDIA_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/addedMedia"
//        private const val MEDIA_COLLECTION = "media"
        private const val GOALS_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/goals"

    }
}

