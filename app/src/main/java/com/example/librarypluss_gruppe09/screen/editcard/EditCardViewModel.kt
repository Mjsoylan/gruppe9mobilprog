package com.example.librarypluss_gruppe09.screen.editcard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.GOAL_ID
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageGoals
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCardViewModel @Inject constructor(
    private val storage: StorageGoals,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val editcard = mutableStateOf(SettDescriptionGoal())

    val editFild = mutableStateOf("")

    init {
        val goalId = savedStateHandle.get<String>(GOAL_ID)
        if (goalId != null) {
            viewModelScope.launch {
                editcard.value = storage.getGoal(goalId) ?: SettDescriptionGoal()
            }
        }

        //todo make a else when the user adds a new goal

    }

    fun updateFild(goalfild: SettDescriptionGoal, updateFild: String) {
        viewModelScope.launch {
            storage.updategoal(goalfild, updateFild)

        }
    }

    fun addGoal(goal: SettDescriptionGoal) {
        viewModelScope.launch {
            storage.createGoal(goal)
        }
    }
}