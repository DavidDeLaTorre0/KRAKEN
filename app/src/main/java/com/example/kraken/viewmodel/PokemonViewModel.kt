package com.example.kraken.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraken.data.model.Pokemon
import com.example.kraken.data.remote.RetrofitInstance
import com.example.kraken.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository(RetrofitInstance.apiService)

    // Usamos un StateFlow para que la UI pueda observar la lista de Pokémon.
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                // Asegúrate de que el repositorio devuelve PokemonListResponse
                val response = repository.getPokemonList(limit = 20, offset = 0)
                _pokemonList.value = response.results // Asigna 'results' a _pokemonList
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }
}
