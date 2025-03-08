package com.example.kraken.views


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kraken.R
import com.example.kraken.ui.componentes.CustomTextField
import com.example.kraken.ui.theme.Boton
import com.example.kraken.ui.theme.ButtonDelete
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.FondoTopBar
import com.example.kraken.ui.theme.Texto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfileScreen(
    db: FirebaseFirestore,
    auth: FirebaseAuth,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onLogoutClick = {
                    auth.signOut()
                    Log.i("Profile", "Estoy saliendo")
                    navigateToLogin()
                },
                onBackClick = {
                    Log.i("Profile", "Estoy CAMBIANDO A HOME")
                    navigateToHome()
                }
            )
        }
    ) { paddingValues ->

        Content(paddingValues, navigateToLogin)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(onLogoutClick: () -> Unit, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Perfil", color = Texto, fontSize = 35.sp) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoTopBar),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Volver",
                    tint = Texto
                )
            }
        },
        actions = {
            IconButton(onClick = onLogoutClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Cerrar sesión",
                    tint = Texto
                )
            }
        }
    )
}

@Composable
fun Content(paddingValues: PaddingValues, navigateToLogin: () -> Unit) {

    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser

    Column(modifier = Modifier
        .padding(paddingValues)
        .background(Fondo)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                // Logo o título
                Text("Ajustes", fontSize = 70.sp, color = Texto)

                Spacer(modifier = Modifier.height(50.dp))

                // Campo de usuario
                CustomTextField(
                    text = "Nuevo nombre usuario:",
                    value = username,
                    onValueChange = { username = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Guardar
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Boton),
                    modifier = Modifier.width(270.dp)
                ) {
                    Text("Guardar", fontSize = 14.sp)
                }



                Spacer(modifier = Modifier.weight(1f)) // Empuja el botón de eliminar hacia abajo

                // Botón Eliminar cuenta
                Button(
                    onClick = {

                        if (user != null) {
                            val uid = user.uid
                            val db = FirebaseFirestore.getInstance()

                            db.collection("usuarios")
                                .document(uid)
                                .delete()
                                .addOnCompleteListener { deleteTask ->
                                    if (deleteTask.isSuccessful) {
                                        user.delete()
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(
                                                        context,
                                                        "Cuenta y datos eliminados con éxito",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    navigateToLogin()
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Error al eliminar la cuenta: ${task.exception?.message}",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Error al eliminar los datos: ${deleteTask.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {

                            Toast.makeText(
                                context,
                                "No hay un usuario autenticado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonDelete),
                    modifier = Modifier.width(270.dp)
                ) {
                    Text("Eliminar cuenta", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
