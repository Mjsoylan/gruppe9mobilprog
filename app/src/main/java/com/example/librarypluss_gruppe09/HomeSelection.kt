package com.example.librarypluss_gruppe09

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSelection(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ },
            actions = {
                IconButton(onClick = { navController.navigate("library") }) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = "Library"
                    )
                }
                IconButton(onClick = { navController.navigate("goals") }) {
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = "Goals"
                    )
                }
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "library",
            modifier = modifier.padding(innerPadding)
        ) {
            composable("library") {
                LibraryScreen()
            }
            composable("goals") {
                GoalsScreen()
            }
        }
    }

}

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    //todo implement database with viweModel and Flow
//    var mList: Map<String, String> by remember {  mutableStateOf (mapOf()) }
    val database = Firebase.firestore


    database.collection("books").get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.i(TAG, "Error getting documents: ${document.data}")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.FixedSize(180.dp),
                content = {
                    items(5) {it -> MediaCard()}
                }, modifier = modifier.padding(16.dp))}
        }
    }


@Composable
fun GoalsScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "goals",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Goals", color = Color.Black)
    }
}