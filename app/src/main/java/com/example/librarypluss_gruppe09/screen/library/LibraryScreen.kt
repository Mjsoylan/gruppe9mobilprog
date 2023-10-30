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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.MediaCard

object DataSource {
//    val medialist = listOf(
//        Media(tittle = "somemovie"),
//        Media(tittle = "Pokemon Emerald")
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(modifier: Modifier = Modifier, viewModel: LibraryViewModel = hiltViewModel()) {
    val medialist = viewModel.media.collectAsStateWithLifecycle(emptyList())


    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            FilterLibrary()

            LazyVerticalGrid(
                    columns = GridCells.FixedSize(180.dp),
                    content = {
                        items(medialist.value, key = { it.mediaId }) { medie ->
                            MediaCard(medie)

                        }
                    }, modifier = modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterLibrary() {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }
        Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp).wrapContentSize(Alignment.TopCenter),
                ) {
            IconButton(onClick = { isExpanded = true }) {
                Text(gender, fontSize = 15.sp)
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
                            gender = "Book"
                            isExpanded = false
                        }
                )
                DropdownMenuItem(
                        text = {
                            Text(text = "Movie", textAlign = TextAlign.Center)
                        },
                        onClick = {
                            gender = "Movie"
                            isExpanded = false
                        }
                )
                DropdownMenuItem(
                        text = {
                            Text(text = "Game", textAlign = TextAlign.Center)
                        },
                        onClick = {
                            gender = "Game"
                            isExpanded = false
                        },
                )
            }
        }
}

