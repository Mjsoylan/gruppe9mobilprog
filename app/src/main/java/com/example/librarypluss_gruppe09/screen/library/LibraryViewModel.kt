package com.example.librarypluss_gruppe09.screen.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.CollectionService
import com.example.librarypluss_gruppe09.firebaseservice.ServiceModule
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageImpl
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LibraryViewModel @Inject
constructor(private val storage: StorageImpl) :
    ViewModel() {
    val media = storage.mediacollection

    //    val medialist = ArrayList<Media>()
    init {
        viewModelScope.launch {
            if (media.first().isEmpty()) {
                DataSource.medialist.forEach { media ->
                    storage.saveMedia(media)

                }
            }
        }
    }

    fun createMovie(movieTitle: String) {
        viewModelScope.launch {
            storage.saveMedia(Media(tittle = movieTitle))
        }
    }

}

