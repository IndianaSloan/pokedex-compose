package com.fueled.list.data

import com.fueled.core.data.BaseApi
import com.fueled.core.data.model.ApiResult
import com.fueled.core.data.model.ResponseApiModel
import com.fueled.list.data.model.NamedApiModel
import com.fueled.list.data.model.PokemonApiModel
import javax.inject.Inject

class PokemonApi @Inject constructor(private val pokemonService: PokemonService) : BaseApi() {

    suspend fun getPokemonList(): ApiResult<ResponseApiModel<List<NamedApiModel>>> =
        apiCall {
            pokemonService.getPokemon()
        }

    suspend fun getPokemonById(pokemonId: String): ApiResult<PokemonApiModel> =
        apiCall {
            pokemonService.getPokemonById(pokemonId)
        }
}
