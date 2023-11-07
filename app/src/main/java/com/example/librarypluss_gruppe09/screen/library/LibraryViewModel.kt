package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LibraryViewModel @Inject
constructor(private val storage: StorageLibrary) :
    ViewModel() {

    //todo change tis to get meda from a user
    val media = storage.mediacollection


    var filter = mutableStateOf("random")


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
        filter.value = "random"
    }

    fun deleteCard(medid: String) {
        viewModelScope.launch {
            //todo implement delete media
//            storage.deleteGoal(medid)
        }
    }

//    init {
//        viewModelScope.launch {
//            if (media.first().isEmpty()) {
//                DataSource.medialist.forEach { media ->
//                    storage.saveMedia(media)
//
//                }
//            }
//        }
//    }

//    fun createMovie(movieTitle: String) {
//        viewModelScope.launch {
//            storage.saveMedia(Media(tittle = movieTitle))
//        }
//    }

}

