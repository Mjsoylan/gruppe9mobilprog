package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarypluss_gruppe09.MediaCard
import com.example.librarypluss_gruppe09.firebaseservice.impl.AccountImp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(modifier: Modifier = Modifier, viewModel: LibraryViewModel = hiltViewModel()) {
    val medialist = viewModel.media.collectAsStateWithLifecycle(emptyList())
    val filtervalu = viewModel.filter.value


    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            //Text(text = "userid: "+username.username,  color = Color.Black)
            FilterLibrary()

            LazyVerticalGrid(
                columns = GridCells.FixedSize(180.dp),
                content = {
                    items(medialist.value, key = { it.mediaId }) { medie ->
                        if (medie.tag == filtervalu) {
                            MediaCard(medie)
                        } else if (filtervalu == "random") {
                            MediaCard(medie)
                        }

                    }
                }, modifier = modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun FilterLibrary(libraryViewModel: LibraryViewModel = viewModel()) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedFirstInOrder by remember {
        mutableStateOf("All")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.TopCenter),
    ) {
        IconButton(onClick = { isExpanded = true }) {
            Text(selectedFirstInOrder, fontSize = 15.sp)
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "Book", textAlign = TextAlign.Center)
                },
                onClick = {
                    selectedFirstInOrder = "Book"
                    isExpanded = false
                    libraryViewModel.setFilterBook()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Movie", textAlign = TextAlign.Center)
                },
                onClick = {
                    selectedFirstInOrder = "Movie"
                    isExpanded = false
                    libraryViewModel.setFilterMovie()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "Game", textAlign = TextAlign.Center)
                },
                onClick = {
                    selectedFirstInOrder = "Game"
                    isExpanded = false
                    libraryViewModel.setFilterGame()

                },
            )
            DropdownMenuItem(
                text = {
                    Text(text = "All", textAlign = TextAlign.Center)
                },
                onClick = {
                    selectedFirstInOrder = "All"
                    isExpanded = false
                    libraryViewModel.setFilterall()

                },
            )
        }
    }
}

