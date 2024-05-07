package com.example.cardiolink.views

import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = null  // Adjust as necessary to match your actual icon
    )

    data object Scan : BottomBarScreen(
        route = "SCAN",
        title = "Scan",
        icon = null  // Adjust as necessary to match your actual icon
    )

    data object Chat : BottomBarScreen(
        route = "CHAT",
        title = "Chat",
        icon = null  // Adjust as necessary
    )

    data object History : BottomBarScreen(
        route = "HISTORY",
        title = "History",
        icon = null
    )

    data object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Profile",
        icon = null
    )
}
