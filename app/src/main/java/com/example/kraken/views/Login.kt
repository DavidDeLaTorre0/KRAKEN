package com.example.kraken.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kraken.ui.componentes.CustomTextField
import com.example.kraken.ui.theme.Boton
import com.example.kraken.ui.theme.Texto
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(auth: FirebaseAuth, navigateToHome: () -> Unit, navigateToLogUp: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            // Logo o título "KRAKEN"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("KRAKEN", fontSize = 80.sp, color = Texto)
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
        item {
            // Campo de usuario
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    text = "Correo:",
                    value = email,
                    onValueChange = { email = it }
                )
            }
        }
        item {
            // Campo de contraseña
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    text = "Contraseña:",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )
            }
        }
        // Mensaje de error
        if (errorMessage.isNotEmpty()) {
            item {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        item {
            // Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                errorMessage = "Email o contraseña no pueden estar vacíos"
                                // errorMessage = stringResource(id = R.string.pokemon_error_message)
                                //stringResource(R.string.error_login_failed)
                                //LocalContext.current.getString(R.string.error_login_failed)
                            } else {
                                errorMessage = ""
                                auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->

                                        if (task.isSuccessful) {

                                            //Navegar
                                            Log.i("LOGIN", "LOGIN OK")
                                            navigateToHome()

                                        } else {

                                            //Error
                                            Log.i("LOGIN", "LOGIN KO")
                                            errorMessage = "Error al iniciar sesión"
                                        }
                                    }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Boton),
                        modifier = Modifier.width(125.dp)
                    ) {
                        Text("Entrar", fontSize = 14.sp)
                    }
                }

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            navigateToLogUp()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Boton),
                        modifier = Modifier.width(125.dp)
                    ) {
                        Text("Registrarse", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}