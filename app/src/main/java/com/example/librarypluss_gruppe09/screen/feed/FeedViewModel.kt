package com.example.librarypluss_gruppe09.screen.feed

import androidx.lifecycle.ViewModel
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject
constructor(private val Feed: StorageLibrary) :
    ViewModel() {

    val activefeed = Feed.feedcollection
}

