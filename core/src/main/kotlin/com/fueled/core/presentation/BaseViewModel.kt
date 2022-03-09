package com.fueled.core.presentation

import androidx.lifecycle.ViewModel
import com.fueled.core.base.ResourceProvider
import com.fueled.core.domain.model.RepositoryError
import kotlinx.coroutines.flow.*
import javax.inject.Inject

abstract class BaseViewModel<STATE : BaseState>(initState: STATE) : ViewModel() {

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    protected var currentState: STATE
        get() = _state.value
        set(value) {
            _state.value = value
        }

    protected fun handleError(e: RepositoryError) {
        displayError(e.message ?: "Something went wrong")
    }

    abstract fun displayError(error: String)
}
