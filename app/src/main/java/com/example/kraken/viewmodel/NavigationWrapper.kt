package com.example.kraken.viewmodel

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kraken.views.LogUpScreen
import com.example.kraken.views.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {

    NavHost(navController = navHostController, startDestination ="initial"){
        composable("logIn"){
            LoginScreen(
                navigateToLogin = {navHostController.navigate("logIn")},
                navigateToHome = {navHostController.navigate("home")}
            )
        }
        composable("logUp"){
            LogUpScreen(
                auth,
                navigateToLogup = {navHostController.navigate("logup")}
            )
        }
        composable("home") {
            HomeScreen(db)
        }
    }
}