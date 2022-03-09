package com.fueled.list.presentation.detail.model

import com.fueled.core.presentation.BaseState

internal data class PokemonDetailState(
    override val isLoading: Boolean = false,
    override val isError: Boolean = false,
    override val errorMessage: String? = null
) : BaseState {

    companion object {
        val Empty = PokemonDetailState()
    }
}
