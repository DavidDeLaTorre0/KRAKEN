package com.example.kraken.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kraken.data.model.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Carga la imagen del Pokémon
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del Pokémon
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
