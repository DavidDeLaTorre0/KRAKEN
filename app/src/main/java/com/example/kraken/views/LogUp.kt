package com.example.kraken.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.kraken.ui.theme.BorderInput
import com.example.kraken.ui.theme.Boton
import com.example.kraken.ui.theme.Input
import com.example.kraken.ui.theme.Texto
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LogUpScreen(auth: FirebaseAuth, navigateToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("")}
    var boolean by remember { mutableStateOf(false)}
    val emailPattern = "^[A-Za-z0-9+_.-]+@(gmail\\.com|yahoo\\.es|outlook\\.com)$".toRegex()

        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo o título "KRAKEN"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("KRAKEN", fontSize = 80.sp, color = Texto)
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Campo de usuario
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text(
                        text = "Nombre usuario:",
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Texto
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderInput,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray,
                            focusedContainerColor = Input,
                        )
                    )
                }
            }
        }

        // Campo de contraseña
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text(
                        text = "Contraseña:",
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Texto
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderInput,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray,
                            focusedContainerColor = Input,
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text(
                        text = "Repita la contraseña:",
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Texto
                    )
                    OutlinedTextField(
                        value = password2,
                        onValueChange = { password2 = it },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderInput,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray,
                            focusedContainerColor = Input,
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text(
                        text = "Correo:",
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Texto
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderInput,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray,
                            focusedContainerColor = Input,
                        )
                    )
                }
            }
        }
        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
        }
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

                        //Validaciones

                        if (password != password2) {
                            errorMessage = "Las contraseñas no coinciden."
                        } else if (email.isBlank() || !email.matches(emailPattern)) {

                            errorMessage = " Correo invalido \n pruebe a usar una de estas extensiones \n (@gmail.com, @yahoo.es, @outlook.com)."
                        }
                        else {
                            errorMessage = ""
                            // Intentar crear el usuario
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.i("REGIS", "Registro OK")
                                        navigateToLogin()
                                    } else {
                                        Log.i("REGIS", "Registro KO")
                                        errorMessage = "El correo ya esta asociado a otra cuenta"
                                    }
                                }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Boton),
                    modifier = Modifier.width(270.dp)
                ) {
                    Text("Registrarse", fontSize = 14.sp)
                }
            }
        }
    }
}




