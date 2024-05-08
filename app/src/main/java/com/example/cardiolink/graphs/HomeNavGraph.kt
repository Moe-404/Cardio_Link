package com.example.cardiolink.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cardiolink.views.BottomBarScreen
import com.example.cardiolink.views.ProfileContent
import com.example.cardiolink.views.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            ScreenContent(
                name = BottomBarScreen.Home.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Scan.route) {
            ScreenContent(
                name = BottomBarScreen.Scan.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Chat.route) {
            ScreenContent(
                name = BottomBarScreen.Chat.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.History.route) {
            ScreenContent(
                name = BottomBarScreen.History.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileContent(
                name = BottomBarScreen.Profile.route,
            )
        }
        //detailsNavGraph(navController = navController)
    }
}

//fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
//    navigation(
//        route = Graph.DETAILS,
//        startDestination = DetailsScreen.Information.route
//    ) {
//        composable(route = DetailsScreen.Information.route) {
//            ScreenContent(name = DetailsScreen.Information.route) {
//                navController.navigate(DetailsScreen.Overview.route)
//            }
//        }
//        composable(route = DetailsScreen.Overview.route) {
//            ScreenContent(name = DetailsScreen.Overview.route) {
//                navController.popBackStack(
//                    route = DetailsScreen.Information.route,
//                    inclusive = false
//                )
//            }
//        }
//    }
//}

