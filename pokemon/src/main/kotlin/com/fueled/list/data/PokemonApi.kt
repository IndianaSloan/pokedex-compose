package com.fueled.list.data

import com.fueled.core.data.BaseApi
import com.fueled.core.data.model.ApiResult
import com.fueled.core.data.model.ResponseApiModel
import com.fueled.list.data.model.PokemonPreviewApiModel
import javax.inject.Inject

class PokemonApi @Inject constructor(private val pokemonService: PokemonService) : BaseApi() {

    suspend fun getPokemonList(): ApiResult<ResponseApiModel<List<PokemonPreviewApiModel>>> = apiCall {
        pokemonService.getPokemon()
    }
}
