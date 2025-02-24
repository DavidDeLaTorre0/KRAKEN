package com.example.kraken.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kraken.data.model.Pokemon
import com.example.kraken.ui.componentes.PokemonCard
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.FondoTopBar
import com.example.kraken.ui.theme.Texto
import com.example.kraken.viewmodel.PokemonViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(db: FirebaseFirestore, auth: FirebaseAuth, navigateToLogin: () -> Unit, viewModel: PokemonViewModel) {


    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())


    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    Scaffold(
        topBar = { TopBar(onLogoutClick = {
            auth.signOut()
            Log.i("HOME", "Estoy saliendo")
            navigateToLogin()
        }) }
    ) { paddingValues ->

        Content(paddingValues, pokemonList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onLogoutClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Hola, Username", color = Texto) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoTopBar),
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
fun Content(paddingValues: PaddingValues, pokemonList: List<Pokemon>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 3 columnas
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Fondo),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}
