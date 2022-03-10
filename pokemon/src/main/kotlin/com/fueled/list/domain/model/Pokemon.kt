package com.fueled.list.domain.model

data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val statistics: List<PokemonStat>,
    val weight: Int,
    val types: List<PokemonType>
)
