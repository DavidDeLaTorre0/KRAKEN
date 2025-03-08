package com.example.kraken

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kraken.navigation.NavigationWrapper
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.KRAKENTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            KRAKENTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Fondo
                ) { paddingValues ->

                    NavigationWrapper(
                        navHostController,
                        auth,
                        db,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }

    //Para evitar seguir loggeado cuandon vuelves a iniciar la app
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Log.i("LOG", "Estoy logado")
            auth.signOut()
            Log.i("LOG", "Estoy deslogado")
        }
    }

}