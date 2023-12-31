package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.screen.Login.LogginScreen
import com.example.librarypluss_gruppe09.screen.Login.signinscreen


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
                LogginScreen(loggedIn = { navController.navigate("home") },
                    Signup = { navController.navigate("signin") })
            }
            composable("home") {
                BottomNavigation()
            }
            composable("signin") {
                signinscreen(loggedIn = { navController.navigate("home") },
                    backtologged = { navController.navigate("singup") })
            }
        }
    }
}