package com.example.librarypluss_gruppe09


import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.models.ConstantsNavigation
import com.example.librarypluss_gruppe09.ui.theme.Pink40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBtweenItems by navController.currentBackStackEntryAsState()
    val current = navBtweenItems?.destination

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = current?.route.toString()) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Pink40,
                content = {
                    ConstantsNavigation.BottomNavItems.forEach { screen ->
                        IconButton(onClick = { navController.navigate(screen.route)}) {
                            Icon(
                                screen.icon,
                                contentDescription = screen.label
                            )
                        }
                    }
                },
                )
        }) { innerPadding ->
            HomeNav(navController = navController, padding = innerPadding)
        }
    }






