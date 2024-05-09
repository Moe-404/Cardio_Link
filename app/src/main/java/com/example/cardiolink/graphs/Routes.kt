package com.example.cardiolink.graphs

object Graph {
    const val RootGraph = "root_graph"
    const val HomeGraph = "home_graph"
    const val AuthGraph = "auth_graph"
}

sealed class AuthRouteScreen(val route: String) {
    data object Login : AuthRouteScreen("login")
    data object SignUp : AuthRouteScreen("signUp")
    data object Forgot : AuthRouteScreen("forgot")
}

sealed class HomeRoutesScreen(val route: String) {
    data object Home : HomeRoutesScreen("home")
    data object Scan : HomeRoutesScreen("scan")
    data object Chat : HomeRoutesScreen("chat")
    data object History : HomeRoutesScreen("history")
    data object Profile : HomeRoutesScreen("profile")
}