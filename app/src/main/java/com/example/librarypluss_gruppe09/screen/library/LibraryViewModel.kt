package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.AccountService
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LibraryViewModel @Inject
constructor(private val storage: StorageLibrary) :
    ViewModel() {
    val media = storage.mediacollection
    var filter = mutableStateOf("")


    fun setFilterBook() {
        filter.value = ""
        filter.value = "book"
    }

    fun setFilterMovie() {
        filter.value = ""
        filter.value = "movie"
    }

    fun setFilterGame() {
        filter.value = ""
        filter.value = "game"
    }

    fun setFilterall() {
        filter.value = ""
        filter.value = ""
    }
}

