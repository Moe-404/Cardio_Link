package com.example.cardiolink.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cardiolink.R
import com.example.cardiolink.graphs.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    homeNavController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(homeNavController = homeNavController) }
    ) {
        HomeNavGraph(
            rootNavController,
            homeNavController
        )
    }
}

@Composable
fun BottomBar(homeNavController: NavHostController) {
    val screens = listOf(
        Utils.Home,
        Utils.Scan,
        Utils.Info,
        Utils.History,
        Utils.Profile,
    )
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }

    val bottomBarDestination = screens.any { it.route == currentRoute }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = navBackStackEntry?.destination,
                    navController = homeNavController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Utils,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val icon = when (screen) {
        Utils.Home -> painterResource(id = R.drawable.home_24)
        Utils.Scan -> painterResource(id = R.drawable.pulse_24)
        Utils.Info -> painterResource(id = R.drawable.messages_24)
        Utils.History -> painterResource(id = R.drawable.history_24)
        Utils.Profile -> painterResource(id = R.drawable.user_24)
    }
    NavigationBarItem(label = {
        Text(text = screen.title)
    }, icon = {
        Icon(
            painter = icon,
            contentDescription = "${screen.title} Navigation Icon"
        )
    }, selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true, onClick = {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }, alwaysShowLabel = false)
}