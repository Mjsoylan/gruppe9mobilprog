package com.example.librarypluss_gruppe09


import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.models.ConstantsNavigation

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ToppApp(navController) },
        bottomBar = { BottomNavFromHome(navController = navController) },
    ) { innerPadding ->
        HomeNavHost(navController = navController, padding = innerPadding)
    }
}

enum class OnScreeen {
    Library,
    Goals,
    Feed,
    Profile,
    Add,
    Login,
    signup;

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToppApp(navController: NavHostController) {
    val onScreentittle by rememberUpdatedState(
        newValue = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: OnScreeen.Library.name
    )
    TopAppBar(
        title = {
            Log.i("somekj", onScreentittle.toString())
            if (onScreentittle.contains("goalId=")) {
                Text(text = "Edit Goal")
            } else if(onScreentittle.contains("mediaId")) {
                Text(text = "Media")
            } else {
                Text(text = onScreentittle)
            }
        },
        actions = {
            // nav to libary or goal while on thoes screens
            if (onScreentittle=="Library" || onScreentittle=="Goals"){
                IconButton(onClick = { navController.navigate(OnScreeen.Library.name) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.libary),
                        contentDescription = "Library"
                    )
                }
                IconButton(onClick = { navController.navigate(OnScreeen.Goals.name) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.goals),
                        contentDescription = "Goals"
                    )
                }
            }
        }
    )
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






