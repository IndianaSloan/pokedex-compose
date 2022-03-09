package com.fueled.composetemplate.presentation.navigation

import androidx.annotation.StringRes
import com.fueled.core.presentation.ScreenArgs
import com.fueled.core_ui_resources.R

/**
 * Top Level Destinations within the app that represent the destinations within a navigation bar.
 * Each destination does not represent an actual screen or UI content, but rather the host for a
 * collection of [Screen].
 */
sealed class Destination(val route: String) {
    object Pokemon : Destination("pokemon")
    object Types : Destination("types")
}

sealed class Screen(private val route: String) {
    fun createRoute(destination: Destination) = "${destination.route}/$route"

    object PokemonList : Screen("list")

    object PokemonDetails : Screen("detail/{${ScreenArgs.EXTRA_POKEMON_ID}}") {
        fun createRoute(destination: Destination, pokemonId: String): String {
            return "${destination.route}/detail/$pokemonId"
        }
    }

    object Demo : Screen("demo")

    companion object {
        @StringRes
        fun getScreenTitleForRoute(route: String?, destination: Destination): Int {
            return when (route) {
                PokemonList.createRoute(destination) -> R.string.destination_pokemon
                PokemonDetails.createRoute(destination) -> R.string.destination_types
                Demo.createRoute(destination) -> R.string.destination_demo
                else -> R.string.app_name
            }
        }

        fun isTopLevelDest(route: String?): Boolean {
            return route == PokemonList.createRoute(Destination.Pokemon) ||
                    route == Demo.createRoute(Destination.Types)
        }
    }
}
