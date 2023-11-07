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
    ViewModel() {


    val goals = storage.goalscollection

    val boolEditingList = mutableStateOf(false)

    //    val addMediaToLibBool = mutableStateOf(false)
    val editList = mutableStateOf("Edit Goals")

    fun editinglist() {
        boolEditingList.value = true
        editList.value = "End Edit"
    }

    fun notEditingList() {
        boolEditingList.value = false
        editList.value = "Edit Goals"
    }

    //Media data class is used as a parameter so the methode knows to add the rigth data
    fun addCardToLibrary(medid: Media) {
        viewModelScope.launch {
            if (boolEditingList.value) {
                storage.sendMediaToLibrary(medid)
                storage.deleteGoal(medid)
            }
        }
    }

    //todo edit goal

}