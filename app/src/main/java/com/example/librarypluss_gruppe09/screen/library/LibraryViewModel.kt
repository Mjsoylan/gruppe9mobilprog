package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import com.example.librarypluss_gruppe09.models.Datasource
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
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

    fun deleteCard(medid: Media) {
        viewModelScope.launch {
            //todo implement delete media
            storage.deleteMedia(medid)
        }
    }

//    init {
//        viewModelScope.launch {
//            if (media.first().isEmpty()) {
//                Datasource.settMedia.forEach { media ->
//                    storage.savemedia(media)
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

