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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.librarypluss_gruppe09.screen.editcard.EditCardScreen
import com.example.librarypluss_gruppe09.screen.goals.GoalsScreen
import com.example.librarypluss_gruppe09.screen.library.LibraryScreen


enum class OnScreeen {
    Library,
    Goals
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSelection(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val onScreentittle by rememberUpdatedState(
        newValue = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: OnScreeen.Library.name
    )

    Scaffold(topBar = {
        TopAppBar(
            title = {
                if (onScreentittle.contains("=")) {
                    Text(text = "Edit Goal")
                } else {
                    Text(text = onScreentittle)
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate(OnScreeen.Library.name) }) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = "Library"
                    )
                }
                IconButton(onClick = { navController.navigate(OnScreeen.Goals.name) }) {
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
            startDestination = OnScreeen.Library.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(OnScreeen.Library.name) {
                LibraryScreen()
            }
            composable(OnScreeen.Goals.name) {
                GoalsScreen(onGoalClick = { goalid ->
                    val route = "${GOAL_EDIT}?$GOAL_ID=$goalid"
                    navController.navigate(route)
                })
            }
            composable(
                route = "${"goalEdit"}$GOAL_ID_ARG",
                arguments = listOf(navArgument(GOAL_ID) {
                    nullable = false
                })
            ) {
                EditCardScreen()
            }
        }
    }
}


