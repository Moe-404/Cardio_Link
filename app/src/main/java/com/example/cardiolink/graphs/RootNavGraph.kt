package com.example.cardiolink.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cardiolink.viewModels.AuthViewModel
import com.example.cardiolink.views.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    val viewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner)[AuthViewModel::class.java]
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController, viewModel = viewModel)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}