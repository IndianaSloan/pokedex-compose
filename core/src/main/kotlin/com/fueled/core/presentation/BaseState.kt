package com.fueled.core.presentation

interface BaseState {
    val isLoading: Boolean
    val isError: Boolean
    val errorMessage: String?
}
