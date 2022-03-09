package com.fueled.list.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.core_ui.extension.rememberFlowOnLifecycle
import com.fueled.core_ui.presentation.components.Screen
import com.fueled.core_ui.theme.Dimens
import com.fueled.list.presentation.list.components.PokemonCard
import com.fueled.list.presentation.list.model.PokemonListState

@Composable
fun PokemonList(
    openPokemonDetail: (String) -> Unit,
) {
    PokemonListScreen(
        viewModel = hiltViewModel(),
        openPokemonDetail = openPokemonDetail,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    openPokemonDetail: (String) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(PokemonListState.Empty)

    Screen {
        LazyVerticalGrid(
            state = rememberLazyListState(),
            cells = GridCells.Adaptive(minSize = Dimens.PokemonGridSize),
        ) {
            items(items = state.data) { pokemonPreview ->
                PokemonCard(
                    pokemonPreview = pokemonPreview,
                    onPokemonSelected = { pokemonId -> openPokemonDetail(pokemonId) },
                )
            }
        }
    }
}
