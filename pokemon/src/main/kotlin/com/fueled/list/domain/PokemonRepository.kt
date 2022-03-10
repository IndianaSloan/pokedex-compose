package com.fueled.list.domain

import com.fueled.core.domain.model.RepositoryResult
import com.fueled.list.domain.model.Pokemon
import com.fueled.list.domain.model.PokemonPreview
import com.fueled.list.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow

internal interface PokemonRepository {

    fun getPokemonList(): Flow<RepositoryResult<PokemonList>>

    fun getPokemon(pokemonId: String): Flow<RepositoryResult<Pokemon>>
}
