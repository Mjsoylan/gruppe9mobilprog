package com.example.librarypluss_gruppe09.screen.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.CollectionService
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

object DataSource {
    val medialist = listOf(
        Media(tittle = "somemovie"),
        Media(tittle = "Pokemon Emerald")
    )
}


@HiltViewModel
class LibraryViewModel @Inject constructor(private val collectionService: CollectionService) :
    ViewModel() {
    val media = collectionService.mediacollection

    //    val medialist = ArrayList<Media>()
    init {
        viewModelScope.launch {
            if (media.first().isEmpty()) {
                DataSource.medialist.forEach { media ->
                    collectionService.saveMedia(media)

                }
            }
        }
    }

    fun createMovie(movieTitle: String) {
        viewModelScope.launch {
            collectionService.saveMedia(Media(tittle = movieTitle))
        }
    }

}

