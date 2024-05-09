package com.example.cardiolink.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardiolink.viewModels.AuthViewModel
import com.example.cardiolink.views.HomeScreen

@Composable
fun RootNavigationGraph() {
    val viewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner)[AuthViewModel::class.java]
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.AuthGraph
    ) {
        authNavGraph(rootNavController = rootNavController, viewModel = viewModel)
        composable(route = Graph.HomeGraph) {
            HomeScreen(rootNavController = rootNavController)
        }
    }
}