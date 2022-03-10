package com.fueled.list.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonApiModel(
    val id: Int,
    val name: String,
    val stats: List<BaseStatApiModel>,
)
