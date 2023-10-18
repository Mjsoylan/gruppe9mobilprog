package com.example.librarypluss_gruppe09.screen.library

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarypluss_gruppe09.models.Media
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

object DataSource {
    val medialist = listOf(
        Media(tittle = "somemovie"),
        Media(tittle = "Pokemon Emerald")
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(modifier: Modifier = Modifier, viewModel: LibraryViewModel = hiltViewModel()) {
    //todo implement database with viweModel and Flow
//    modifier: Modifier = Modifier, viewModel: LibraryViewModel =


    val medialist = viewModel.media.collectAsStateWithLifecycle(emptyList())



//    database.collection("books").get()
//        .addOnSuccessListener { result ->
//            for (document in result) {
//                Log.i(ContentValues.TAG, "Error getting documents: ${document.data}")
//            }
//        }.addOnFailureListener { exception ->
//            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
//        }

//    val medielist = viewModel.media.collectAsStateWithLifecycle(emptyList())
//    val medielist = viewModel.media.collectAsStateWithLifecycle(emptyList())

//    Log.i("mytag", medielist.toString())


    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(180.dp),
                content = {
                    items(medialist.value, key = { it.mediaId }) { medie ->
                        Box {
                            Text(text = medie.tittle, textAlign = TextAlign.Center)
                        }
                    }
                }, modifier = modifier.padding(16.dp)
            )
        }
    }
}