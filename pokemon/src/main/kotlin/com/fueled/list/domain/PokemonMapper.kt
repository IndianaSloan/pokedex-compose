package com.fueled.list.domain

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import com.fueled.core.data.ApiConst
import com.fueled.core.domain.BaseMapper
import com.fueled.list.data.model.PokemonPreviewApiModel
import com.fueled.list.domain.model.PokemonPreview
import com.fueled.list.domain.model.PokemonList
import com.fueled.list.domain.model.toPokemonList
import javax.inject.Inject

internal class PokemonMapper @Inject constructor() : BaseMapper() {

    fun mapPokemonList(list: List<PokemonPreviewApiModel>): PokemonList = list.map { pokemon ->
        // Sample URL: "https://pokeapi.co/api/v2/pokemon/1/"
        val id = pokemon.url.split("/".toRegex()).dropLast(1).last()
        PokemonPreview(
            id = id,
            name = pokemon.name.capitalize(Locale.current),
            imageUrl = ApiConst.BASE_IMAGE_URL.format(id)
        )
    }.toPokemonList()
}
