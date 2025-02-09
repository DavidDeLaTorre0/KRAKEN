package com.example.kraken.views

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kraken.ui.theme.Botones
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.Inputs

@Preview(showBackground = true, backgroundColor = 0xFF7FA1C3)
@Composable
fun LoginScreen(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            Text("KRAKEN", fontSize = 80.sp)
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
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("LordSalchicha") },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray
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
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("********") },
                        shape = RoundedCornerShape(36.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            unfocusedContainerColor = Color.LightGray
                        )
                    )
                }
            }
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
                    onClick = { /* Acción del botón 1 */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Botones),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Entrar", fontSize = 14.sp)
                }
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Button(
                    onClick = { /* Acción del botón 2 */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Botones),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Registrarse", fontSize = 14.sp)
                }
            }
        }
    }
}