package com.example.cardiolink.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cardiolink.viewModels.AuthViewModel
import com.example.cardiolink.views.ForgotPasswordScreen
import com.example.cardiolink.views.LoginScreen
import com.example.cardiolink.views.RegisterScreen
import com.example.cardiolink.views.SplashScreen

fun NavGraphBuilder.authNavGraph(rootNavController: NavHostController, viewModel: AuthViewModel) {
    navigation(
        route = Graph.AuthGraph,
        startDestination = AuthScreen.SPLASH.route
    ) {

        composable(route = AuthScreen.SPLASH.route) {
            SplashScreen(navController = rootNavController)
        }

        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController = rootNavController, viewModel)
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(navController = rootNavController, viewModel)
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(navController = rootNavController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object SPLASH : AuthScreen(route = "SPLASH")
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
    data object Forgot : AuthScreen(route = "FORGOT")
}