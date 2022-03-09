package com.fueled.list.domain.model

data class PokemonPreview(
    val id: String,
    val name: String,
    val imageUrl: String,
    val dominantColor: Int? = null
)
