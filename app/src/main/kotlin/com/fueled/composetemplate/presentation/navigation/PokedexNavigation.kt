package com.fueled.composetemplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fueled.core.presentation.ScreenArgs
import com.fueled.core_ui.presentation.demo.DemoScreen
import com.fueled.core_ui_resources.R
import com.fueled.list.presentation.detail.PokemonDetail
import com.fueled.list.presentation.list.PokemonList

@Composable
internal fun PokedexNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    setToolbarTitle: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Destination.Pokemon.route
    ) {
        addPokemonListTopLevel(navController, setToolbarTitle)
        addTypesTopLevel()
    }
}

private fun NavGraphBuilder.addPokemonListTopLevel(
    navController: NavController,
    setToolbarTitle: (String) -> Unit,
) {
    navigation(
        route = Destination.Pokemon.route,
        startDestination = Screen.PokemonList.createRoute(Destination.Pokemon)
    ) {
        addPokemonList(navController, Destination.Pokemon, setToolbarTitle)
        addPokemonDetail(Destination.Pokemon, setToolbarTitle)
    }
}

private fun NavGraphBuilder.addPokemonList(
    navController: NavController,
    destination: Destination,
    setToolbarTitle: (title: String) -> Unit,
) {
    composable(
        route = Screen.PokemonList.createRoute(destination)
    ) {
        setToolbarTitle(stringResource(id = R.string.destination_pokemon))
        PokemonList(
            openPokemonDetail = { pokemonId ->
                val route = Screen.PokemonDetails.createRoute(destination, pokemonId)
                navController.navigate(route)
            }
        )
    }
}

private fun NavGraphBuilder.addPokemonDetail(
    destination: Destination,
    setToolbarTitle: (String) -> Unit,
) {
    composable(
        route = Screen.PokemonDetails.createRoute(destination),
        arguments = listOf(navArgument(ScreenArgs.EXTRA_POKEMON_ID) { type = NavType.StringType })
    ) {
        PokemonDetail(
            setToolbarTitle = setToolbarTitle
        )
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
