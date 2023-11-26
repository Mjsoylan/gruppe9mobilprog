package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarypluss_gruppe09.ui.theme.BlueMoviePrimary
import com.example.librarypluss_gruppe09.ui.theme.Purple80
import com.example.librarypluss_gruppe09.ui.theme.RedGamePrimary
import com.example.librarypluss_gruppe09.ui.theme.YellowBookPrimary

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = hiltViewModel(),
    onMediaClick: (String) -> Unit
) {
    val medialist = viewModel.media.collectAsStateWithLifecycle(emptyList())
    val filtervalu = viewModel.filter.value


    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            FilterLibrary()
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                content = {
                    if (filtervalu != "") {
                        items(medialist.value.filter {
                            it.tag == filtervalu
                        }, key = { it.mediaId }) { medie ->
                            MediaCard(medie, onMediaClick = onMediaClick)
                        }

                    } else {
                        items(medialist.value, key = { it.mediaId }) { medie ->
                            MediaCard(medie, onMediaClick = onMediaClick)
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
            .padding(2.dp)
            .wrapContentSize(Alignment.Center),
    ) {
        IconButton(
            onClick = { isExpanded = true }, modifier =
            Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                .width(200.dp)
        ) {
            Text(selectedFirstInOrder, fontSize = 15.sp)
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            modifier = Modifier
                .padding(2.dp)
                .width(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))

            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Book", textAlign = TextAlign.Center)
                    },
                    onClick = {
                        selectedFirstInOrder = "Book"
                        isExpanded = false
                        libraryViewModel.setFilterBook()
                    },
                    modifier = Modifier
                        .background(YellowBookPrimary)
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Movie", textAlign = TextAlign.Center)
                    },
                    onClick = {
                        selectedFirstInOrder = "Movie"
                        isExpanded = false
                        libraryViewModel.setFilterMovie()
                    },
                    modifier = Modifier
                        .background(BlueMoviePrimary)
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
                    modifier = Modifier
                        .background(RedGamePrimary)

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
                    modifier = Modifier
                        .background(Purple80)
                )
            }

        }
    }
}

