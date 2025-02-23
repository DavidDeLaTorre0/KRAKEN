package com.example.kraken.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.kraken.ui.componentes.PokemonCard
import com.example.kraken.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel) {

    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())


    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }


    LazyColumn {
        items(pokemonList) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}
