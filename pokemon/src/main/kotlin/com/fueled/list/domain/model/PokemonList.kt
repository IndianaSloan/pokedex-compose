package com.fueled.list.domain.model

internal class PokemonList(vararg items: PokemonPreview) : ArrayList<PokemonPreview>(listOf(*items))

internal fun List<PokemonPreview>.toPokemonList() = PokemonList(*this.toTypedArray())
