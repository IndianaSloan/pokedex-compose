package com.fueled.list.presentation.list.model

import com.fueled.core.presentation.BaseState
import com.fueled.list.domain.model.PokemonList
import javax.annotation.concurrent.Immutable

@Immutable
internal data class PokemonListState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    override val isError: Boolean = false,
    val hasDataLoaded: Boolean = false,
    val data: PokemonList = PokemonList(),
    val firstVisibleItemIndex: Int = 0,
) : BaseState {

    companion object {
        val Empty = PokemonListState()
    }
}
