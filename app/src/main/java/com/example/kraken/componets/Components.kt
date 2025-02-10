package com.example.kraken.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kraken.ui.theme.BorderInput
import com.example.kraken.ui.theme.Input
import com.example.kraken.ui.theme.Texto

@Composable
fun CustomTextField(text: String,
           value: String,
           onValueChange: (String) -> Unit){
    Box {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Text(
                text = text,
                modifier = Modifier.padding(bottom = 4.dp),
                color = Texto
            )
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
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