package com.example.kraken

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.KRAKENTheme
import com.example.kraken.viewmodel.NavigationWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KRAKENTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Fondo
                ) {
                    NavigationWrapper(navHostController, auth, db)
                }
            }
        }
    }

    //Para evitar seguir loggeado cuandon vuelves a iniciar la app
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Log.i("aris", "Estoy logado")
            auth.signOut()
            Log.i("aris", "Estoy deslogado")
        }
    }

}