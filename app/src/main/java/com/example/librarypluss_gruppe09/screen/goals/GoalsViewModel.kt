package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageImpl
import com.example.librarypluss_gruppe09.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

@HiltViewModel

class GoalsViewModel @Inject
constructor(private val storage: StorageImpl) :
    ViewModel(){

    val goals = storage.goalscollection

    val addMediaToLibBool = mutableStateOf(false)

    fun settAddToLibrary(){
        addMediaToLibBool.value = false
        addMediaToLibBool.value = true
    }

    fun deleteCard(medid: String){
        viewModelScope.launch {
            storage.deleteGoal(medid)
        }
    }

}