package com.fueled.list.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.fueled.core.domain.model.RepositoryResult
import com.fueled.core.domain.model.onError
import com.fueled.core.domain.model.onSuccess
import com.fueled.core.presentation.BaseViewModel
import com.fueled.core.presentation.ScreenArgs
import com.fueled.list.domain.PokemonRepository
import com.fueled.list.domain.model.PokemonPreview
import com.fueled.list.presentation.detail.model.PokemonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository,
) : BaseViewModel<PokemonDetailState>(PokemonDetailState.Empty) {

    private val pokemonId: String = savedStateHandle.get(ScreenArgs.EXTRA_POKEMON_ID)!!

    init {
        getData(pokemonId)
    }

    private fun getData(pokemonId: String) = viewModelScope.launch {
        pokemonRepository.getPokemon(pokemonId)
            .onEach(::onPokemonRetrieved)
            .collect()
    }

    private fun onPokemonRetrieved(result: RepositoryResult<PokemonPreview>) {
        result.onSuccess { pokemon ->
            // GOT POKEMON
        }.onError(::handleError)
    }

    override fun displayError(error: String) {
        currentState = currentState.copy(
            isLoading = false,
            isError = true,
            errorMessage = error,
        )
    }
}
