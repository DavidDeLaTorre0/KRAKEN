package com.example.kraken.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kraken.R
import com.example.kraken.data.model.Pokemon
import com.example.kraken.ui.componentes.PokemonCard
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.FondoTopBar
import com.example.kraken.ui.theme.Texto
import com.example.kraken.viewmodel.PokemonViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    auth: FirebaseAuth,
    navigateToLogin: () -> Unit,
    viewModel: PokemonViewModel,
    navigateToProfile: () -> Unit
) {


    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    var searchText by remember { mutableStateOf("") }


    val filteredPokemonList = pokemonList.filter { pokemon ->
        pokemon.name.contains(searchText, ignoreCase = true)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                onLogoutClick = {
                    auth.signOut()
                    Log.i("HOME", "Estoy saliendo")
                    navigateToLogin()
                },
                onProfileClick = {
                    Log.i("USR", "Estoy CAMBIANDO A PROFILE")
                    navigateToProfile()
                }
            )

        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(searchText = searchText, onSearchTextChange = { searchText = it })
            if (pokemonList.isEmpty()) {

                ErrorMessage(paddingValues)
            } else {

                Content(filteredPokemonList)
            }
        }
    }
}

@Composable
fun ErrorMessage(paddingValues: PaddingValues) {
    Text(
        text = "Error al cargar los Pokémon. Intenta nuevamente.",
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Fondo)
            .padding(16.dp),
        color = Color.Red,
        style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onLogoutClick: () -> Unit, onProfileClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Hola, Username", color = Texto) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoTopBar),
        navigationIcon = {
            IconButton(onClick = onProfileClick) {
                Icon(
                    painter = painterResource(id = R.drawable.account_circle),
                    contentDescription = "Cerrar sesión",
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
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = { Text("Buscar Pokémon...") },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true
    )
}

@Composable
fun Content(pokemonList: List<Pokemon>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 3 columnas
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}
