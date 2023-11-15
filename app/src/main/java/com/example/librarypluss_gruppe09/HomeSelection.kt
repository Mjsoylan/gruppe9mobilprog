package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.screen.goals.GoalsScreen
import com.example.librarypluss_gruppe09.screen.library.LibraryScreen
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSelection(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val user = FirebaseAuth.getInstance().currentUser
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ },

            actions = {
                //temp for now will be username later when impl TODO
                if (user != null) {
                    Text(text = "userid: "+user.uid,  color = Color.Black)
                }
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


