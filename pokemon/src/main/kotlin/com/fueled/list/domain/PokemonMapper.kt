package com.fueled.list.domain

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.fueled.core.data.ApiConst
import com.fueled.core.domain.BaseMapper
import com.fueled.list.data.model.BaseStatApiModel
import com.fueled.list.data.model.PokemonApiModel
import com.fueled.list.data.model.PokemonPreviewApiModel
import com.fueled.list.domain.model.Pokemon
import com.fueled.list.domain.model.PokemonList
import com.fueled.list.domain.model.PokemonPreview
import com.fueled.list.domain.model.PokemonStat
import com.fueled.list.domain.model.toPokemonList
import javax.inject.Inject

internal class PokemonMapper @Inject constructor() : BaseMapper() {

    fun mapPokemonList(list: List<PokemonPreviewApiModel>): PokemonList = list.map { pokemon ->
        // Sample URL: "https://pokeapi.co/api/v2/pokemon/1/"
        val id = pokemon.url.parseUrlId()
        PokemonPreview(
            id = id,
            name = pokemon.name.capitalize(Locale.current),
            imageUrl = ApiConst.POKEMON_IMAGE_URL.format(id)
        )
    }.toPokemonList()

    fun mapPokemon(pokemon: PokemonApiModel): Pokemon {
        val id = pokemon.id.toString()
        return Pokemon(
            id = id,
            name = pokemon.name.capitalize(Locale.current),
            imageUrl = ApiConst.POKEMON_IMAGE_URL.format(id),
            statistics = mapPokemonStats(pokemon.stats)
        )
    }

    private fun mapPokemonStats(stats: List<BaseStatApiModel>): List<PokemonStat> {
        return stats.map { statApiModel ->
            val id = statApiModel.stat.url.parseUrlId()
            PokemonStat(
                id = id,
                name = statApiModel.stat.name,
                value = statApiModel.value
            )
        }
    }

    private fun String.parseUrlId(): String = this.split("/".toRegex()).dropLast(1).last()
}
