package com.example.librarypluss_gruppe09.screen.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Preview
    @Composable
    fun FeedScreen(modifier: Modifier = Modifier, viewModel: FeedViewModel = hiltViewModel()) {
        val medialist = viewModel.activefeed.collectAsStateWithLifecycle(emptyList())
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(Alignment.TopCenter),
            ) {
                Text(text = "Feed")
            }
            LazyVerticalGrid(
                columns = GridCells.FixedSize(360.dp),
                content = {

                        items(medialist.value.sortedByDescending {it.uploadtime}, key = { it.mediaId }) { medie ->

                            Feedmediacard(medie)
                        }
                    }, modifier = modifier.padding(20.dp,0.dp,10.dp)

            )
        }
    }
    }




