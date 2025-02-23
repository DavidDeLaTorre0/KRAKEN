package com.example.kraken.data.repository

import com.example.kraken.data.model.PokemonListResponse
import com.example.kraken.data.remote.ApiService

class PokemonRepository(private val apiService: ApiService) {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return apiService.getPokemonList(limit, offset)
    }
}
