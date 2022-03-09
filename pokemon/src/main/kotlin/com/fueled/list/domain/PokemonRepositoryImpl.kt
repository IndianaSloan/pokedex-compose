package com.fueled.list.domain

import com.fueled.core.base.DispatcherProvider
import com.fueled.core.domain.model.RepositoryError
import com.fueled.core.domain.model.RepositoryResult
import com.fueled.core.domain.model.flatMap
import com.fueled.list.data.PokemonApi
import com.fueled.list.data.model.PokemonPreviewApiModel
import com.fueled.list.domain.model.PokemonPreview
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

    private val cache = mutableListOf<PokemonPreview>()

    override fun getPokemonList(): Flow<RepositoryResult<PokemonList>> = flow {
        val result = fetchPokemon().flatMap(pokemonMapper::mapPokemonList)
        emit(result)
    }.flowOn(dispatcherProvider.io)

    override fun getPokemon(pokemonId: String): Flow<RepositoryResult<PokemonPreview>> = flow {
        val result = try {
            RepositoryResult.success(cache.first { it.id == pokemonId })
        } catch (e: NoSuchElementException) {
            RepositoryResult.error(RepositoryError.fromException(e))
        }
        emit(result)
    }

    override suspend fun updatePokemon(pokemonPreview: PokemonPreview) {
        val index = cache.indexOfFirst { it.id == pokemonPreview.id }
        with(cache) {
            removeAt(index)
            add(index, pokemonPreview)
        }
    }

    private suspend fun fetchPokemon(): RepositoryResult<List<PokemonPreviewApiModel>> {
        return pokemonApi.getPokemonList().run {
            pokemonMapper.mapApiResponse(this, "[GET] /pokemon")
        }
    }
}
