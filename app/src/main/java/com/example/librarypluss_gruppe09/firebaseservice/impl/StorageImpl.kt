package com.example.librarypluss_gruppe09.firebaseservice.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.librarypluss_gruppe09.firebaseservice.CollectionService
import com.example.librarypluss_gruppe09.models.Media
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageImpl
@Inject
constructor(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore,
) : CollectionService {

    override val MEDIA_COLLECTION="user/"+auth.currentUser?.uid+"/addedMedia"

    override val GOALS_COLLECTION="user/"+auth.currentUser?.uid+"/goals"
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
        firestore.collection(GOALS_COLLECTION).document(mediaId).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }



    //todo querry based on the user id as a key to get det addetmedia to media collection

}

