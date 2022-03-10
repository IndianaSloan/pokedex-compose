package com.fueled.list.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.fueled.core.data.ApiConst
import com.fueled.core.data.PokemonApiTypes
import com.fueled.core.data.PokemonApiTypes.BUG
import com.fueled.core.data.PokemonApiTypes.DARK
import com.fueled.core.data.PokemonApiTypes.DRAGON
import com.fueled.core.data.PokemonApiTypes.ELECTRIC
import com.fueled.core.data.PokemonApiTypes.FAIRY
import com.fueled.core.data.PokemonApiTypes.FIGHTING
import com.fueled.core.data.PokemonApiTypes.FIRE
import com.fueled.core.data.PokemonApiTypes.FLYING
import com.fueled.core.data.PokemonApiTypes.GHOST
import com.fueled.core.data.PokemonApiTypes.GRASS
import com.fueled.core.data.PokemonApiTypes.GROUND
import com.fueled.core.data.PokemonApiTypes.ICE
import com.fueled.core.data.PokemonApiTypes.NORMAL
import com.fueled.core.data.PokemonApiTypes.POISON
import com.fueled.core.data.PokemonApiTypes.PSYCHIC
import com.fueled.core.data.PokemonApiTypes.ROCK
import com.fueled.core.data.PokemonApiTypes.SHADOW
import com.fueled.core.data.PokemonApiTypes.STEEL
import com.fueled.core.data.PokemonApiTypes.UNKNOWN
import com.fueled.core.data.PokemonApiTypes.WATER
import com.fueled.core.domain.BaseMapper
import com.fueled.list.data.model.NamedApiModel
import com.fueled.list.data.model.PokemonApiModel
import com.fueled.list.data.model.StatApiModel
import com.fueled.list.data.model.TypeApiModel
import com.fueled.list.domain.model.Pokemon
import com.fueled.list.domain.model.PokemonList
import com.fueled.list.domain.model.PokemonPreview
import com.fueled.list.domain.model.PokemonStat
import com.fueled.list.domain.model.PokemonType
import com.fueled.list.domain.model.StatisticType
import com.fueled.list.domain.model.StatisticType.ATTACK
import com.fueled.list.domain.model.StatisticType.DEFENSE
import com.fueled.list.domain.model.StatisticType.HP
import com.fueled.list.domain.model.StatisticType.SPECIAL_ATTACK
import com.fueled.list.domain.model.StatisticType.SPECIAL_DEFENSE
import com.fueled.list.domain.model.StatisticType.SPEED
import com.fueled.list.domain.model.toPokemonList
import javax.inject.Inject

internal class PokemonMapper @Inject constructor() : BaseMapper() {

    fun mapPokemonList(list: List<NamedApiModel>): PokemonList = list.map { pokemon ->
        // Sample URL: "https://pokeapi.co/api/v2/pokemon/1/"
        val id = pokemon.url.parseUrlId()
        PokemonPreview(
            id = id,
            name = pokemon.name.capitalize(Locale.current),
            imageUrl = ApiConst.POKEMON_IMAGE_URL.format(id)
        )
    }.toPokemonList()

    fun mapPokemon(apiModel: PokemonApiModel): Pokemon {
        val id = apiModel.id.toString()
        return Pokemon(
            id = id,
            name = apiModel.name.capitalize(Locale.current),
            imageUrl = ApiConst.POKEMON_IMAGE_URL.format(id),
            statistics = mapPokemonStats(apiModel.stats),
            weight = apiModel.weight,
            types = apiModel.types.map(::mapPokemonType)
        )
    }

    private fun mapPokemonStats(stats: List<StatApiModel>): List<PokemonStat> {
        return stats.map { statApiModel ->
            val id = statApiModel.stat.url.parseUrlId()
            PokemonStat(
                id = id,
                name = statApiModel.stat.name,
                color = mapStatisticColor(statApiModel.stat.name),
                rawValue = statApiModel.value,
                strength = calculateStatStrength(statApiModel.value),
            )
        }
    }

    private fun mapStatisticColor(statName: String): Color {
        return when (StatisticType.values().first { it.statName == statName }) {
            HP -> Color(0xFFD33A47)
            ATTACK -> Color(0xFFFAAA22)
            DEFENSE -> Color(0xFF0391F0)
            SPECIAL_ATTACK -> Color(0xFFB33B17)
            SPECIAL_DEFENSE -> Color(0xFFA52DFC)
            SPEED -> Color(0xFF90AFC5)
        }
    }

    private fun mapPokemonType(apiModel: TypeApiModel): PokemonType {
        val typeName = apiModel.type.name.capitalize(Locale.current)
        return PokemonType(
            id = apiModel.type.url.parseUrlId(),
            name = typeName,
            color = mapTypeColor(typeName)
        )
    }

    private fun mapTypeColor(name: String): Color {
        return when (PokemonApiTypes.valueOf(name.toUpperCase(Locale.current))) {
            NORMAL -> Color(0xFFB1A5A5)
            FIGHTING -> Color(0xFF9F422A)
            FLYING -> Color(0xFF90B1C5)
            POISON -> Color(0xFF642785)
            GROUND -> Color(0xFFAD7235)
            ROCK -> Color(0xFF4B190E)
            BUG -> Color(0xFF179A55)
            GHOST -> Color(0xFF363069)
            STEEL -> Color(0xFF5C756D)
            FIRE -> Color(0xFFB22328)
            WATER -> Color(0xFF2648DC)
            GRASS -> Color(0xFF007C42)
            ELECTRIC -> Color(0xFFE0E64B)
            PSYCHIC -> Color(0xFFAC296B)
            ICE -> Color(0xFF7ECFF2)
            DRAGON -> Color(0xFF378A94)
            DARK -> Color(0xFF040706)
            FAIRY -> Color(0xFF9E1A44)
            SHADOW -> Color(0xFFB1A5A5)
            UNKNOWN -> Color(0xFFB1A5A5)
        }
    }

    private fun calculateStatStrength(statValue: Int): Float {
        return (statValue.toFloat() / 200.toFloat())
    }

    private fun String.parseUrlId(): String = this.split("/".toRegex()).dropLast(1).last()
}
