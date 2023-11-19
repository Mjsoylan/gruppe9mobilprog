package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageGoals
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject
constructor(private val storage: StorageGoals) :
    ViewModel() {

    //flow
    val history = storage.historycollection
    val mediagoals = storage.mediascollection
    val settgoals = storage.settgoalscollection
    val stategoals = mutableStateOf("")


//    init {
//        viewModelScope.launch {
////            if (mediagoals.first().isEmpty()) {
//////                media
////                Datasource.settMedia.forEach { media ->
////                    storage.savemedia(media)
////
////                }
////
////            }
//            if (history.first().isEmpty()) {
//                //goals
//                Datasource.settGoals.forEach { goal ->
//                    storage.addGoal(goal)
//                }
//            }
//        }
//    }

    fun deleteMediaCard(medid: Media) {
        viewModelScope.launch {
            storage.deleteMedia(medid)
            storage.sendMediaToHistory(medid)
        }
    }
    fun deleteGoalCard(goal: SettDescriptionGoal) {
        viewModelScope.launch {
            storage.deleteGoal(goal)
            //todo add a list or change History data class to only store a string and date
//            storage.sendGoalToHistory(goal)
        }
    }
    //show one of the collections
    fun go_to_edit_goals() {
        stategoals.value = "editgoals"
    }
    fun go_to_media_goals() {
        stategoals.value = "mediagoals"
    }
    fun go_to_history_goals() {
        stategoals.value = "historygoals"
    }

}