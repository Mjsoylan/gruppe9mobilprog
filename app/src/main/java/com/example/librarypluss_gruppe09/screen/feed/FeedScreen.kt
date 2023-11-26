package com.example.librarypluss_gruppe09.screen.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.screen.library.MediaCard
import com.example.librarypluss_gruppe09.ui.theme.ConnectionState
import com.example.librarypluss_gruppe09.ui.theme.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    onMediaClick: (String) -> Unit
) {
    val medialist = viewModel.activefeed.collectAsStateWithLifecycle(emptyList())

//https://medium.com/scalereal/observing-live-connectivity-status-in-jetpack-compose-way-f849ce8431c7
    val networkconection by connectivityState()

    val boolConnection = networkconection === ConnectionState.Available

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (boolConnection) {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.FixedSize(360.dp),
                        content = {


                            items(
                                medialist.value.sortedByDescending { it.uploadtime },
                                key = { it.mediaId }) { medie ->
                                val mediaconvert = Media(
                                    medie.mediaId,
                                    medie.tittle,
                                    medie.creator,
                                    medie.type,
                                    medie.tag,
                                    medie.imageUrl,
                                )
                                MediaCard(mediaconvert, onMediaClick = onMediaClick)


                            }
                        }, modifier = modifier.padding(20.dp, 0.dp, 10.dp)
                    )
                }
            } else {
                Text(
                    text = "no connection",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}




