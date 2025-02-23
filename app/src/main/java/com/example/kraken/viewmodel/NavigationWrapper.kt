package com.example.kraken.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kraken.views.HomeScreen
import com.example.kraken.views.LogUpScreen
import com.example.kraken.views.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore,
    modifier: Any
) {

    NavHost(navController = navHostController, startDestination = "logIn") {
        composable("logIn") {
            LoginScreen(
                auth,
                navigateToHome = { navHostController.navigate("home") },
                navigateToLogUp = { navHostController.navigate("logUp") }
            )
        }
        composable("logUp") {
            LogUpScreen(
                auth,
                navigateToLogin = { navHostController.navigate("logIn") }
            )
        }
        composable("home") {
            HomeScreen(
                db,
                auth,
                navigateToLogin = { navHostController.navigate("logIn") }
            )
        }
    }
}