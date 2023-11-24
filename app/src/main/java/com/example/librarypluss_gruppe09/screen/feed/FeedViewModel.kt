package com.example.librarypluss_gruppe09.screen.feed

import androidx.lifecycle.ViewModel
import com.example.librarypluss_gruppe09.firebaseservice.AccountService
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject
constructor(private val Feed: StorageLibrary, private val account: AccountService) :
    ViewModel() {

    val activefeed = Feed.mediacollection

    val userfeed = Feed.UsersCollection
    val useruid = FirebaseAuth.getInstance().currentUser!!.uid
}

