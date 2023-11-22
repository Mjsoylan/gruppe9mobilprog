package com.example.librarypluss_gruppe09.screen.feed

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarypluss_gruppe09.db
import com.example.librarypluss_gruppe09.firebaseservice.User
import com.example.librarypluss_gruppe09.models.Feedmedia
import com.example.librarypluss_gruppe09.models.UserAccunt
import com.example.librarypluss_gruppe09.screen.library.FilterLibrary
import com.example.librarypluss_gruppe09.screen.library.LibraryViewModel
import com.example.librarypluss_gruppe09.screen.library.MediaCard
import com.example.librarypluss_gruppe09.ui.theme.BlueMoviePrimary
import com.example.librarypluss_gruppe09.ui.theme.Purple80
import com.example.librarypluss_gruppe09.ui.theme.RedGamePrimary
import com.example.librarypluss_gruppe09.ui.theme.YellowBookPrimary
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow


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
                Text(text = "feed")
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




