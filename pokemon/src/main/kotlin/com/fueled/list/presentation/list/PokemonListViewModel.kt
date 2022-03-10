package com.fueled.list.presentation.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.fueled.core.domain.model.RepositoryResult
import com.fueled.core.domain.model.onError
import com.fueled.core.domain.model.onSuccess
import com.fueled.core.presentation.BaseViewModel
import com.fueled.core_ui.extension.onError
import com.fueled.core_ui.extension.onSuccess
import com.fueled.list.domain.PokemonRepository
import com.fueled.list.domain.model.PokemonList
import com.fueled.list.presentation.list.model.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PokemonListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository,
) : BaseViewModel<PokemonListState>(PokemonListState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            currentState = currentState.copy(isLoading = true)
            pokemonRepository.getPokemonList()
                .onEach(::onPokemonListRetrieved)
                .collect()
        }
    }

    private fun onPokemonListRetrieved(result: RepositoryResult<PokemonList>) {
        result.onSuccess { pokemonList ->
            currentState = currentState.copy(
                isLoading = false,
                hasDataLoaded = true,
                isError = false,
                data = pokemonList,
            )
        }.onError(::handleError)
    }

    override fun displayError(error: String) {
        currentState = currentState.copy(
            isLoading = false,
            isError = true,
            errorMessage = error
        )
    }
}
