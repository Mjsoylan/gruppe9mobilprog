package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.screen.add.AddScreen
import com.example.librarypluss_gruppe09.screen.editcard.EditCardScreen
import com.example.librarypluss_gruppe09.screen.feed.FeedScreen
import com.example.librarypluss_gruppe09.screen.goals.GoalsScreen
import com.example.librarypluss_gruppe09.screen.library.LibraryScreen
import com.example.librarypluss_gruppe09.screen.profile.ProfileScreen
import com.example.librarypluss_gruppe09.screen.profile.ProfileViewModel

@Composable
fun HomeNavHost(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home", modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable("home") {
            LibraryScreen()
        }
        composable("feed") {
            FeedScreen()
        }
        composable("profile") {
            ProfileScreen(navController, viewModel = ProfileViewModel(media = Media()))
        }
        composable("add") {
            AddScreen()
        }

        //within the Library screen
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
            EditCardScreen(navController)
        }
    }
}
