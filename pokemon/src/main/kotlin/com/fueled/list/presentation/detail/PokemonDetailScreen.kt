package com.fueled.list.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.fueled.core_ui.extension.rememberFlowOnLifecycle
import com.fueled.core_ui.presentation.components.Screen
import com.fueled.core_ui.theme.Dimens
import com.fueled.core_ui_resources.R
import com.fueled.list.presentation.detail.components.PokemonTypesRow
import com.fueled.list.presentation.detail.components.StatisticBar
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

    Screen {
        state.pokemon?.let { pokemon ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.size(Dimens.PokemonDetailImageSize),
                    painter = rememberImagePainter(pokemon.imageUrl),
                    contentDescription = stringResource(id = R.string.content_desc_pokemon_image)
                )
                Spacer(modifier = Modifier.height(Dimens.PaddingHalf))
                PokemonTypesRow(types = pokemon.types)
                Spacer(modifier = Modifier.height(Dimens.PaddingHalf))
                pokemon.statistics.map { statistic ->
                    StatisticBar(statistic = statistic)
                    Spacer(modifier = Modifier.height(Dimens.PaddingDefault))
                }
            }
        }
    }
}
