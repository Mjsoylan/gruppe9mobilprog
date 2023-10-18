package com.example.librarypluss_gruppe09.screen.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.MediaCard
import com.example.librarypluss_gruppe09.models.Media

object DataSource {
    val medialist = listOf(
        Media(tittle = "somemovie"),
        Media(tittle = "Pokemon Emerald")
    )
}

@Composable
fun LibraryScreen(modifier: Modifier = Modifier, viewModel: LibraryViewModel = hiltViewModel()) {
    val medialist = viewModel.media.collectAsStateWithLifecycle(emptyList())

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(180.dp),
                content = {
                    items(medialist.value, key = { it.mediaId }) { medie ->
                        MediaCard(medie)
//                        Box {
//                            Text(text = medie.tittle, textAlign = TextAlign.Center)
//                        }
                    }
                }, modifier = modifier.padding(16.dp)
            )
        }
    }
}