package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.librarypluss_gruppe09.screen.feed.FeedScreen

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
            Profile()
        }
        composable("add") {
            AddScreen()
        }
    }
}
