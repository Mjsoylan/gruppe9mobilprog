package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.DialogComponent
import com.example.librarypluss_gruppe09.models.History
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal

@Composable
fun GoalsScreen(
    modifier: Modifier = Modifier,
    onGoalClick: (String) -> Unit,
    viewModel: GoalsViewModel = hiltViewModel()
) {
//todo

//    val filtervalu = viewModel.boolEditingList.value

    val screenState = viewModel.stategoals.value

    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                //media goals
                Button(
                    onClick = {
                        viewModel.go_to_media_goals()
                    }, modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = "Media")
                }
                //Settgoals
                Button(
                    onClick = {
                        viewModel.go_to_edit_goals()
                    }, modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = "Goal")
                }
                //history goals
                Button(
                    onClick = {
                        viewModel.go_to_history_goals()
                    }, modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = "History")
                }
            }

            when (screenState) {
                "editgoals" -> EditGoal(viewModel, onGoalClick)
                "historygoals" -> HistoryGoal(viewModel)
                "mediagoals" -> MedaGoal(viewModel)
                else -> {
                    MedaGoal(viewModel)
                }
            }
        }

        FloatingActionButton(
            onClick = { onGoalClick("SettDescriptionGoal(description = ") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}

@Composable
fun MedaGoal(viewModel: GoalsViewModel = hiltViewModel()) {
    val goalViweModel = viewModel.mediagoals.collectAsStateWithLifecycle(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
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
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.TopCenter)
    )
}

@Composable
fun EditGoal(viewModel: GoalsViewModel = hiltViewModel(), onGoalClick: (String) -> Unit) {
    val settgoalsViweModel = viewModel.settgoals.collectAsStateWithLifecycle(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        content = {
            items(settgoalsViweModel.value, key = { it.goalId }) { edit ->
//                        if(medie.tag == filtervalu){
//                            MediaCard(medie)
//                        }
//                        else if (filtervalu == "random") {
//                            MediaCard(medie)
//                        }
                SettGoalCard(edit, onGoalClick = onGoalClick)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun HistoryGoal(viewModel: GoalsViewModel = hiltViewModel()) {

    val historyViewModel = viewModel.history.collectAsStateWithLifecycle(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        content = {
            items(historyViewModel.value, key = { it.historyId }) { history ->
//                        if(medie.tag == filtervalu){
//                            MediaCard(medie)
//                        }
//                        else if (filtervalu == "random") {
//                            MediaCard(medie)
//                        }
                HistoryCard(history)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.TopCenter)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettGoalCard(
    goal: SettDescriptionGoal,
    onGoalClick: (String) -> Unit,
    viewModel: GoalsViewModel = hiltViewModel()
) {
    Card(
        onClick = {
            onGoalClick(goal.goalId)

        },
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
                Button(
                    onClick = { viewModel.alertDeleteCard.value = true }, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete goal")
                }
            }
        }
    }

    when {
        viewModel.alertDeleteCard.value -> {
            DialogComponent(
                onDismissRequest = { viewModel.alertDeleteCard.value = false },
                onConfirmation = {
                    viewModel.alertDeleteCard.value = false
                    viewModel.deleteGoalCard(goal)
                },
                dialogTitle = "Delete goal",
                dialogText = "Move goal to history",
//                todo add titel to SettGoal
//                dialogText = " ${goal.tittle} move to history",
                icon = Icons.Default.Info
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(
    history: History,
//    viewModel: GoalsViewModel = hiltViewModel()
) {
    Card(
        onClick = {

        },
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
                        .height(90.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Box {
                        Text(text = "tittle: ${history.tittle}", textAlign = TextAlign.Center)
                    }

                    Box {
                        Text(text = "You deleted goal at : ${history.date}", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

