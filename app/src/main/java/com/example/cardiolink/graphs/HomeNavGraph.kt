package com.example.cardiolink.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cardiolink.views.HistoryContent
import com.example.cardiolink.views.HomeContent
import com.example.cardiolink.views.InfoContent
import com.example.cardiolink.views.ProfileContent
import com.example.cardiolink.views.ScanContent
import com.example.cardiolink.views.Utils

@Composable
fun HomeNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController
) {
    NavHost(
        navController = homeNavController,
        route = Graph.HomeGraph,
        startDestination = Utils.Home.route
    ) {
        composable(route = Utils.Home.route) {
            HomeContent()
        }
        composable(route = Utils.Scan.route) {
            ScanContent()
        }
        composable(route = Utils.Info.route) {
            InfoContent()
        }
        composable(route = Utils.History.route) {
            HistoryContent()
        }
        composable(route = Utils.Profile.route) {
            ProfileContent(rootNavController = rootNavController)
        }
    }
}