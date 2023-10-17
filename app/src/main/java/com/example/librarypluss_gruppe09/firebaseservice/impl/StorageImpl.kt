package com.example.librarypluss_gruppe09.firebaseservice.impl

import com.example.librarypluss_gruppe09.firebaseservice.CollectionService
import com.example.librarypluss_gruppe09.models.Media
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

    override suspend fun saveMedia(media: Media): String =
        firestore.collection(MEDIA_COLLECTION).add(media).await().id

    companion object {
        private const val MEDIA_COLLECTION = "medie"
    }
}

