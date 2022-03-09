package com.fueled.list.data

import com.fueled.core.data.ApiParams
import com.fueled.core.data.Endpoints
import com.fueled.core.data.model.ResponseApiModel
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

    @GET("${Endpoints.POKEMON}/{pokemonId}")
    suspend fun getPokemonById(
        @Path("pokemonId") pokemonId: String,
    ): Response<ResponseApiModel<PokemonPreviewApiModel>>
}
