package com.fueled.list.data

import com.fueled.core.data.ApiParams
import com.fueled.core.data.ApiPaths
import com.fueled.core.data.Endpoints
import com.fueled.core.data.model.ResponseApiModel
import com.fueled.list.data.model.PokemonApiModel
import com.fueled.list.data.model.PokemonPreviewApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET(Endpoints.POKEMON)
    suspend fun getPokemon(
        @Query(ApiParams.LIMIT) limit: Int = 151,
        @Query(ApiParams.OFFSET) offset: Int = 0
    ): Response<ResponseApiModel<List<PokemonPreviewApiModel>>>

    @GET("${Endpoints.POKEMON}/{${ApiPaths.POKEMON_ID}}")
    suspend fun getPokemonById(
        @Path(ApiPaths.POKEMON_ID) pokemonId: String,
    ): Response<PokemonApiModel>
}
