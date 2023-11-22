package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.screen.profile.ProfileScreen
import com.example.librarypluss_gruppe09.screen.profile.ProfileViewModel

@Composable
fun HomeNav(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home", modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable("home") {
            HomeScreen()
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

    }
}
