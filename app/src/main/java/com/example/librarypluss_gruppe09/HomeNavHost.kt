package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageLibrary
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.screen.Login.LogginScreen
import com.example.librarypluss_gruppe09.screen.Login.signinscreen
import com.example.librarypluss_gruppe09.screen.MediaScreen.MediaScreen
import com.example.librarypluss_gruppe09.screen.add.AddScreen
import com.example.librarypluss_gruppe09.screen.editcard.EditCardScreen
import com.example.librarypluss_gruppe09.screen.feed.FeedScreen
import com.example.librarypluss_gruppe09.screen.goals.GoalsScreen
import com.example.librarypluss_gruppe09.screen.library.LibraryScreen
import com.example.librarypluss_gruppe09.screen.profile.ProfileScreen
import com.example.librarypluss_gruppe09.screen.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun HomeNavHost(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = OnScreeen.Library.name, modifier = Modifier.padding(paddingValues = padding)
    ) {
//        composable(LIBRARY) {
//            LibraryScreen(onMediaClick = { mediaid ->
//                val route = "$LIBRARY?$MEDIA_ID=$mediaid"
//                navController.navigate(route)
//            })
//        }
//        composable("home"){
//            LibraryScreen()
//        }

        composable(OnScreeen.Feed.name) {
            FeedScreen(onMediaClick = { mediaid ->
                val route = "${FEED}?$MEDIA_ID=$mediaid"
                navController.navigate(route)
            })
        }
        composable(OnScreeen.Profile.name) {
            ProfileScreen(navController, viewModel = ProfileViewModel(media = Media()))
        }
        composable(OnScreeen.Add.name) {
            AddScreen()
        }

        //within the Library screen
        composable(OnScreeen.Library.name) {
            LibraryScreen(onMediaClick = { mediaid ->
                val route = "${LIBRARY}?$MEDIA_ID=$mediaid"
                navController.navigate(route)
            })
        }

        composable(
            route = "${LIBRARY}$MEDIA_ID_ARG",
            arguments = listOf(navArgument(MEDIA_ID) {
                nullable = false
            })
        ) {
            MediaScreen(navController)
        }
        composable(
            route = "${FEED}$MEDIA_ID_ARG",
            arguments = listOf(navArgument(MEDIA_ID) {
                nullable = false
            })
        ) {
            MediaScreen(navController)
        }

        composable(OnScreeen.Goals.name) {
            GoalsScreen(onGoalClick = { goalid ->
                val route = "${GOAL_EDIT}?$GOAL_ID=$goalid"
                navController.navigate(route)
            }, onMediaClick = {mediaid ->
                val route = "${LIBRARY}?$MEDIA_ID=$mediaid"
                navController.navigate(route)
            })
        }

        composable(
            route = "${GOAL_EDIT}$GOAL_ID_ARG",
            arguments = listOf(navArgument(GOAL_ID) {
                nullable = false
            })
        ) {
            EditCardScreen(navController)
        }
        composable(OnScreeen.Login.name) {
            LogginScreen(loggedIn = { navController.navigate(OnScreeen.Library.name) },
                Signup = { navController.navigate(OnScreeen.signup.name) })
        }
        composable(OnScreeen.signup.name) {
            signinscreen(loggedIn = { navController.navigate(OnScreeen.Library.name) },
                backtologged = { navController.navigate(OnScreeen.Login.name) })
        }
    }
}
