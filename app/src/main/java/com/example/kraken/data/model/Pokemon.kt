package com.example.kraken.data.model

data class Pokemon(
    val name:String,
    val url: String
) {
    val imageUrl: String
        get() {
            val id = url.split("/").dropLast(1).last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }
}
