package com.example.librarypluss_gruppe09.firebaseservice.impl

import android.content.ContentValues
import android.util.Log
import com.example.librarypluss_gruppe09.firebaseservice.LibraryService
import com.example.librarypluss_gruppe09.models.Feedmedia
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.UserAccunt
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageLibrary
@Inject
constructor(
    private val firestore: FirebaseFirestore, private val auth: FirebaseAuth,
) : LibraryService {

    override val USER_COOLECTION = "user"

    override val FEED_COLLECTION = "addedmeida"

    override val MEDIA_COLLECTION = "user/" + auth.currentUser?.uid + "/addedMedia"

    override val GOALS_COLLECTION = "user/" + auth.currentUser?.uid + "/goals"

    override val mediacollection: Flow<List<Media>>
        get() = firestore.collection(MEDIA_COLLECTION).dataObjects()
    override val feedcollection: Flow<List<Feedmedia>>
        get() = firestore.collection(FEED_COLLECTION).dataObjects()

    override val UsersCollection: Flow<List<UserAccunt>>
        get() = firestore.collection(USER_COOLECTION).dataObjects()


    override suspend fun getMedia(mediaId: String): Media? =
        firestore.collection(MEDIA_COLLECTION).document(mediaId).get().await().toObject()



    override suspend fun addMediaToLibrary(mediaId: Media): String =
        firestore.collection(MEDIA_COLLECTION).add(mediaId).await().id

    override suspend fun deleteMedia(mediaId: Media): Task<Void> =
        firestore.collection(MEDIA_COLLECTION).document(mediaId.mediaId).delete()
            .addOnSuccessListener {
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot successfully deleted!"
                )
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error deleting document", e) }


    override suspend fun savemedia(mediaId: Media): String =
//        val mediaUserId = mediaId.copy(userId = curr)
        firestore.collection(MEDIA_COLLECTION).add(mediaId).await().id


    companion object {
        private const val MEDIA_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/addedMedia"

    }
}
