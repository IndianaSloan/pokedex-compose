package com.fueled.list.presentation.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.core_ui.extension.rememberFlowOnLifecycle
import com.fueled.core_ui.presentation.components.Screen
import com.fueled.list.presentation.detail.model.PokemonDetailState

@Composable
fun PokemonDetail() {
    PokemonDetailScreen(
        viewModel = hiltViewModel()
    )
}

@Composable
internal fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(PokemonDetailState.Empty)

    Screen {
        Text(text = "Hello, World!")
    }
}
