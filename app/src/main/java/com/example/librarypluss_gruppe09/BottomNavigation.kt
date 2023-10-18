package com.example.librarypluss_gruppe09


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.models.ConstantsNavigation
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavFromHome(navController = navController) },
    ) { innerPadding ->
        HomeNav(navController = navController, padding = innerPadding)
    }
}

@Composable
fun BottomNavFromHome(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        ConstantsNavigation.BottomNavItems.forEach { screen ->
            val title = screen.label

            NavigationBarItem(selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = title
                    )
                },
                label = { Text(text = screen.label) })
        }

    }
}






