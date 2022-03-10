package com.fueled.list.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonApiModel(
    val id: Int,
    val name: String,
    val stats: List<StatApiModel>,
    val weight: Int,
    val types: List<TypeApiModel>,
)
