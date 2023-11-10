package com.example.librarypluss_gruppe09.screen.goals

import android.graphics.BitmapRegionDecoder
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.GoalCard
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalsScreen(modifier: Modifier = Modifier, viewModel: GoalsViewModel = hiltViewModel()) {
    val goalViweModel = viewModel.goals.collectAsStateWithLifecycle(emptyList())
    val settgoalsViweModel = viewModel.settgoals.collectAsStateWithLifecycle(emptyList())

//    val filtervalu = viewModel.filter.value

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Button(
                onClick = {
                    if (viewModel.boolEditingList.value) {
                        viewModel.notEditingList()
                    } else {
                        viewModel.editinglist()
                    }

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {

                Text(text = viewModel.editList.value)

            }
            LazyVerticalGrid(
                columns = GridCells.FixedSize(180.dp),
                content = {
                    items(goalViweModel.value, key = { it.mediaId }) { medie ->
//                        if(medie.tag == filtervalu){
//                            MediaCard(medie)
//                        }
//                        else if (filtervalu == "random") {
//                            MediaCard(medie)
//                        }
                        GoalCard(medie)
                    }
                    items(settgoalsViweModel.value, key = { it.goalId }) { goal ->
                        //figur out how to filter a collection and get some documents containgin specific fild
                        SettGoalCard(goal)
                    }
                }, modifier = modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettGoalCard(goal: SettDescriptionGoal) {
    Card(
        onClick = {},
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)

    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Box {
                        Text(text = goal.description, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

