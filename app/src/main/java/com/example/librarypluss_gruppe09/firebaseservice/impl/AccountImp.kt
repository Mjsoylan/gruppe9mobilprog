package com.example.librarypluss_gruppe09.firebaseservice.impl

import com.example.librarypluss_gruppe09.firebaseservice.User
import com.example.librarypluss_gruppe09.firebaseservice.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
class AccountImp
@Inject
constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid) } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }
    override suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { onResult(it.exception) }.await()
    }

    override suspend fun fastloggin() {
        //temp for working
        auth.signInWithEmailAndPassword("mads.soyland@gmail.com", "password")
    }


    override suspend fun createaccunt(email: String, password: String, onResult: (Throwable?) -> Unit) {
       auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { onResult(it.exception) }.await()
    }

    override suspend fun signOut() {
        auth.signOut()

        auth.signInAnonymously()
    }
    /*
    //todo querry based on the user id as a key to get det addetmedia to media collection
    companion object {
        private const val MEDIA_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/addedMedia"
        //        private const val MEDIA_COLLECTION = "media"
        private const val GOALS_COLLECTION = "user/gRGLI4BDi9QvCVjT5OaI/goals"

    }
    */

}