package com.example.librarypluss_gruppe09.screen.MediaScreen

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.GOAL_ID
import com.example.librarypluss_gruppe09.MEDIA_ID
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val storage: StorageLibrary,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val media = mutableStateOf(Media())
    init {
        val mediaid = savedStateHandle.get<String>(MEDIA_ID)

        if (mediaid != null) {
            viewModelScope.launch {
                if ((storage.getMedia(mediaid) ?: Media().mediaId) != ""){
                    media.value = storage.getMedia(mediaid) ?: Media()
                }
                else if ((storage.getFeedMedia(mediaid) ?: Media().mediaId) != ""){
                    media.value = storage.getFeedMedia(mediaid) ?: Media()
                }
                else if ((storage.getGoalMedia(mediaid) ?: Media().mediaId) != "") {
                    media.value = storage.getGoalMedia(mediaid) ?: Media()
                }
            }
        }
    }
}