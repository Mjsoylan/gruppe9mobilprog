package com.example.librarypluss_gruppe09.firebaseservice.impl

import com.example.librarypluss_gruppe09.firebaseservice.LibraryService
import com.example.librarypluss_gruppe09.models.Media
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageLibrary
@Inject
    constructor(private val firestore: FirebaseFirestore) : LibraryService {

    override val mediacollection: Flow<List<Media>>
        get() = firestore.collection(MEDIA_COLLECTION).dataObjects()

    override suspend fun getMedia(mediaId: String): Media? {
        TODO("Not yet implemented")
    }

//    override suspend fun getMedia(mediaId: String): Media =
//        firestore.collection(MEDIA_COLLECTION).document(mediaId).get().await().toObject()


    companion object {
        private const val MEDIA_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/addedMedia"

        //        private const val MEDIA_COLLECTION = "media"

    }
}
