package com.example.kraken.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraken.data.model.Pokemon
import com.example.kraken.data.repository.PokemonRepository
import com.example.kraken.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository(RetrofitInstance.apiService)


    private val _pokemonList = MutableLiveData<List<Pokemon>>(emptyList())
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonList(limit = 20, offset = 0)
                _pokemonList.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
