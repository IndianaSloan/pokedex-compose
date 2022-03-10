package com.fueled.list.domain

import com.fueled.core.base.DispatcherProvider
import com.fueled.core.domain.model.RepositoryResult
import com.fueled.core.domain.model.flatMap
import com.fueled.list.data.PokemonApi
import com.fueled.list.data.model.NamedApiModel
import com.fueled.list.data.model.PokemonApiModel
import com.fueled.list.domain.model.Pokemon
import com.fueled.list.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val pokemonApi: PokemonApi,
    private val pokemonMapper: PokemonMapper
) : PokemonRepository {

    override fun getPokemonList(): Flow<RepositoryResult<PokemonList>> = flow {
        val result = fetchPokemon().flatMap(pokemonMapper::mapPokemonList)
        emit(result)
    }.flowOn(dispatcherProvider.io)

    override fun getPokemon(pokemonId: String): Flow<RepositoryResult<Pokemon>> = flow {
        val result = fetchPokemonById(pokemonId).flatMap(pokemonMapper::mapPokemon)
        emit(result)
    }

    private suspend fun fetchPokemon(): RepositoryResult<List<NamedApiModel>> {
        return pokemonApi.getPokemonList().run {
            pokemonMapper.mapApiResponse(this, "[GET] /pokemon")
        }
    }

    private suspend fun fetchPokemonById(pokemonId: String): RepositoryResult<PokemonApiModel> {
        return pokemonApi.getPokemonById(pokemonId).run {
            pokemonMapper.mapApiResult(this, "[GET] /pokemon/$pokemonId")
        }
    }
}
