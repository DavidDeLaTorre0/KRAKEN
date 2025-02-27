package com.example.kraken.views

import android.annotation.SuppressLint
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
import com.google.android.play.core.integrity.bd
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@SuppressLint("SuspiciousIndentation")
@Composable
fun LogUpScreen(auth: FirebaseAuth, db:FirebaseFirestore, navigateToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("")}
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()


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
            CustomTextField(
                text = "Nombre usuario:",
                value = username,
                onValueChange = { username = it }
            )
        }

        // Campo de contraseña
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomTextField(
                text = "Contraseña:",
                value = password,
                onValueChange = { password = it },
                isPassword = true,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomTextField(
                text = "Repita la contraseña:",
                value = password2,
                onValueChange = { password2 = it },
                isPassword = true
            )
        }

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

                            errorMessage = "Correo invalido, reviselo"
                        }
                        else {
                                if(username.isBlank() || password.isBlank()){
                                    errorMessage = "No puedes dejar campos vacios"
                                }else{
                                    errorMessage = ""
                                    // Intentar crear el usuario
                                    auth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val user = FirebaseAuth.getInstance().currentUser;

                                                val userMap = hashMapOf(
                                                    "nombre" to username,
                                                    "email" to email
                                                )

                                                if (user != null) {
                                                    db.collection("usuarios")
                                                        .document(user.uid)
                                                        .set(userMap).addOnCompleteListener{
                                                            Log.i("CUsuario", "Success")
                                                        }.addOnFailureListener{
                                                            Log.i("CUsuario", "Failure")
                                                        }        .addOnCompleteListener{
                                                            Log.i("CUsuario", "Complete")
                                                        }
                                                }

                                                Log.i("REGIS", "Registro OK")
                                                navigateToLogin()
                                            } else {
                                                Log.i("REGIS", "Registro KO")
                                                errorMessage = "El correo ya esta asociado a otra cuenta"
                                            }
                                        }
                                }

                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Boton),
                    modifier = Modifier.width(270.dp)
                ) {
                    Text("Registrarse", fontSize = 14.sp)
                }


                Button(
                    onClick = { navigateToLogin() },
                    colors = ButtonDefaults.buttonColors(containerColor = Boton),
                    modifier = Modifier.width(270.dp)
                ) {
                    Text("Volver", fontSize = 14.sp)
                }
            }
        }
    }
}




