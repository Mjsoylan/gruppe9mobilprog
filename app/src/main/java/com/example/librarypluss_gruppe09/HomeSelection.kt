package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.librarypluss_gruppe09.screen.editcard.EditCardScreen
import com.example.librarypluss_gruppe09.screen.goals.GoalsScreen
import com.example.librarypluss_gruppe09.screen.library.LibraryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSelection(modifier: Modifier = Modifier, ) {
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
                IconButton(onClick = { navController.navigate(GOALS_LIST) }) {
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
            composable("goalslist") {
                GoalsScreen(onGoalClick = { goalid ->
                    val route = "${GOAL_EDIT}?$GOAL_ID=$goalid"
                    navController.navigate(route)
                })
            }
//            composable(route = "${"goalEdit"}$GOAL_ID_ARG",
//                arguments = listOf(navArgument(GOAL_ID) {
//                    nullable = false
//                })
//            ) {
//                EditCardScreen()
//            }
            composable(route = "${"goalEdit"}$GOAL_ID_ARG",
                arguments = listOf(navArgument(GOAL_ID) {
                    nullable = false
                })
            ) {
                EditCardScreen()
            }
        }
    }
}


