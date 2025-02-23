package com.example.kraken.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.kraken.ui.componentes.PokemonCard
import com.example.kraken.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel) {
    // Usamos collectAsState para convertir el StateFlow en un valor Composable observable
    val pokemonList by viewModel.pokemonList.collectAsState(emptyList())

    // Llamamos a fetchPokemonList cuando la pantalla se cargue
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    // Usamos LazyColumn para mostrar la lista de Pokémon
    LazyColumn {
        items(pokemonList) { pokemon -> // 'items' pasa la lista y el contenido a mostrar
            PokemonCard(pokemon) // Componente que muestra cada Pokémon
        }
    }
}

