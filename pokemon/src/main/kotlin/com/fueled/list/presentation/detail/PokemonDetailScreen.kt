package com.fueled.list.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.fueled.core_ui.extension.rememberFlowOnLifecycle
import com.fueled.core_ui.presentation.components.Screen
import com.fueled.list.domain.model.Pokemon
import com.fueled.list.presentation.detail.components.PokemonTypesRow
import com.fueled.list.presentation.detail.model.PokemonDetailState

@Composable
fun PokemonDetail(
    setToolbarTitle: (String) -> Unit,
) {
    PokemonDetailScreen(
        viewModel = hiltViewModel(),
        setToolbarTitle = setToolbarTitle,
    )
}

@Composable
internal fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
    setToolbarTitle: (String) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(PokemonDetailState.Empty)

    LaunchedEffect(state.pokemon) {
        setToolbarTitle(state.pokemon?.name ?: "")
    }

    ScreenContent(pokemon = state.pokemon)
}

@Composable
private fun ScreenContent(pokemon: Pokemon?) {
    Screen {
        pokemon?.let { pokemon ->
            Column {
                Text(text = pokemon.name)
                Image(
                    painter = rememberImagePainter(pokemon.imageUrl),
                    contentDescription = null
                )
                PokemonTypesRow(types = pokemon.types)
                pokemon.statistics.map { statistic ->
                    Text(text = "${statistic.name}: ${statistic.rawValue}")
                }
            }
        }
    }
    
}

@Preview(showSystemUi = true, showBackground = true, apiLevel = 26)
@Composable
fun PokemonDetailScreen_Preview() {
    val pokemon = Pokemon(
        id = "1",
        name = "Bulbasaur",
        imageUrl = "",
        statistics = emptyList(),
        weight = 100,
        types = emptyList()
    )
    ScreenContent(pokemon = pokemon)
}
