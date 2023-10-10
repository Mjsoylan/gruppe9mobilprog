package com.example.librarypluss_gruppe09.models


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.selects.select

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    //add selected
)

object ConstantsNavigation {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Feed",
            icon = Icons.Filled.KeyboardArrowDown,
            route = "feed"
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.KeyboardArrowDown,
            route = "profile"
        ),
        BottomNavItem(
            label = "Add",
            icon = Icons.Filled.Add,
            route = "add"
        )
    )
}