package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageGoals
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class GoalsViewModel @Inject
constructor(private val storage: StorageGoals) :
    ViewModel(){

    val goals = storage.goalscollection

    val addMediaToLibBool = mutableStateOf(false)

    fun settAddToLibrary(){
        addMediaToLibBool.value = true
    }

    fun addCardToLibrary(medid: Media){
        viewModelScope.launch {
//            storage.addMediaToLibrary(medid)
            storage.addMediaToLibrary(medid)
        }
    }

}