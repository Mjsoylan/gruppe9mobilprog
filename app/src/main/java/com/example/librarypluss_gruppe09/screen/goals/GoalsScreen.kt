package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarypluss_gruppe09.GoalCard
import com.example.librarypluss_gruppe09.MediaCard
import com.example.librarypluss_gruppe09.models.Media

@Composable
fun GoalsScreen(modifier: Modifier = Modifier, viewModel: GoalsViewModel = hiltViewModel()) {
    val goalViweModel = viewModel.goals.collectAsStateWithLifecycle(emptyList())

//    val filtervalu = viewModel.filter.value

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Button(
                onClick = { viewModel.settAddToLibrary() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                Text(text = "add to library")
            }

            LazyVerticalGrid(
                columns = GridCells.FixedSize(180.dp),
                content = {
                    items(goalViweModel.value, key = {it.mediaId }) { medie ->
//                        if(medie.tag == filtervalu){
//                            MediaCard(medie)
//                        }
//                        else if (filtervalu == "random") {
//                            MediaCard(medie)
//                        }
                        GoalCard(medie)

                    }
                }, modifier = modifier.padding(16.dp)
            )
        }
    }
}

