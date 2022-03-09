package com.fueled.composetemplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fueled.core.presentation.ScreenArgs
import com.fueled.core_ui.presentation.demo.DemoScreen
import com.fueled.list.presentation.detail.PokemonDetail
import com.fueled.list.presentation.list.PokemonList

@Composable
internal fun PokedexNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Destination.Pokemon.route
    ) {
        addPokemonListTopLevel(navController)
        addTypesTopLevel()
    }
}

private fun NavGraphBuilder.addPokemonListTopLevel(navController: NavController) {
    navigation(
        route = Destination.Pokemon.route,
        startDestination = Screen.PokemonList.createRoute(Destination.Pokemon)
    ) {
        addPokemonList(navController, Destination.Pokemon)
        addPokemonDetail(Destination.Pokemon)
    }
}

private fun NavGraphBuilder.addPokemonList(navController: NavController, destination: Destination) {
    composable(
        route = Screen.PokemonList.createRoute(destination)
    ) {
        PokemonList(
            openPokemonDetail = { pokemonId ->
                val route = Screen.PokemonDetails.createRoute(destination, pokemonId)
                navController.navigate(route)
            }
        )
    }
}

private fun NavGraphBuilder.addPokemonDetail(
    destination: Destination
) {
    composable(
        route = Screen.PokemonDetails.createRoute(destination),
        arguments = listOf(
            navArgument(ScreenArgs.EXTRA_POKEMON_ID) { type = NavType.StringType },
            navArgument(ScreenArgs.EXTRA_POKEMON_NAME) { type = NavType.StringType }
        )
    ) {
        PokemonDetail()
    }
}

private fun NavGraphBuilder.addTypesTopLevel() {
    navigation(
        route = Destination.Types.route,
        startDestination = Screen.Demo.createRoute(Destination.Types)
    ) {
        addDemo(Destination.Types)
    }
}

private fun NavGraphBuilder.addDemo(destination: Destination) {
    composable(
        route = Screen.Demo.createRoute(destination)
    ) {
        DemoScreen()
    }
}
