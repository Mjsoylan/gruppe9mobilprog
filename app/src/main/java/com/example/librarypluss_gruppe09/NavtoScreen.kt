package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.screen.Login.LogginScreen
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavtoScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "singup",
            modifier = modifier.padding(innerPadding)
        ) {
            composable("singup") {
                LogginScreen(loggedIn = { navController.navigate("home") })
            }
            composable("home") {
                BottomNavigation()
            }
        }
    }
}