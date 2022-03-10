package com.fueled.list.domain.model

import androidx.compose.ui.graphics.Color

/**
 * A domain definition of a Pokemon's Statistic
 *
 * @param name The name of the statistic (i.e HP or Attack)
 * @param rawValue The raw value as retrieved from the API.
 * @param strength The percentage of the max value of this stat.
 */
data class PokemonStat(
    val id: String,
    val name: String,
    val rawValue: Int,
    val strength: Float,
    val color: Color,
)
