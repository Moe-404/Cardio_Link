package com.example.cardiolink.views

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Utils(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {
    data object Home : Utils(
        route = "Home",
        title = "Home",
        icon = null
    )

    data object Scan : Utils(
        route = "SCAN",
        title = "Scan",
        icon = null
    )

    data object Info : Utils(
        route = "INFO",
        title = "Info",
        icon = null
    )

    data object History : Utils(
        route = "HISTORY",
        title = "History",
        icon = null
    )

    data object Profile : Utils(
        route = "PROFILE",
        title = "Profile",
        icon = null
    )
}
