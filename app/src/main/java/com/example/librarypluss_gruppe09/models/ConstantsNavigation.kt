package com.example.librarypluss_gruppe09.models


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.librarypluss_gruppe09.OnScreeen

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

//https://www.geeksforgeeks.org/bottom-navigation-bar-in-android-jetpack-compose/
object ConstantsNavigation {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = OnScreeen.Library.name
        ),
        BottomNavItem(
            label = "Feed",
            icon = Icons.Filled.KeyboardArrowDown,
            route = OnScreeen.Feed.name
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.AccountBox,
            route = OnScreeen.Profile.name
        ),
        BottomNavItem(
            label = "Add",
            icon = Icons.Filled.Add,
            route = OnScreeen.Add.name

        )
    )
}