package com.example.kraken.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kraken.ui.componentes.CustomTextField
import com.example.kraken.ui.theme.Boton
import com.example.kraken.ui.theme.ButtonDelete
import com.example.kraken.ui.theme.FondoTopBar
import com.example.kraken.ui.theme.Texto

@Composable
fun ProfileScreen(){
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->

        Content(paddingValues)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Perfil", color = Texto, fontSize = 35.sp) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoTopBar),
        actions = {
            IconButton(onClick = {}) {
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
fun Content(paddingValues: PaddingValues) {
    var username by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Logo o título
            Text("Usuario", fontSize = 70.sp, color = Texto)

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
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonDelete),
                modifier = Modifier.width(270.dp)
            ) {
                Text("Eliminar cuenta", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espacio inferior para evitar que toque el borde
        }
    }
}
