package com.example.librarypluss_gruppe09.firebaseservice

import kotlinx.coroutines.flow.Flow
import com.example.librarypluss_gruppe09.firebaseservice.User

interface  AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)

    suspend fun fastloggin()
    suspend fun createaccunt(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun signOut()
}