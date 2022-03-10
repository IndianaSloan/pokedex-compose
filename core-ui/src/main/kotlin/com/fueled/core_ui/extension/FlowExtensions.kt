package com.fueled.core_ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.fueled.core.domain.model.RepositoryError
import com.fueled.core.domain.model.RepositoryResult
import com.fueled.core.domain.model.onError
import com.fueled.core.domain.model.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> rememberFlowOnLifecycle(
    flow: Flow<T>,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> = remember(flow, lifecycle) {
    flow.flowWithLifecycle(
        lifecycle = lifecycle,
        minActiveState = minActiveState,
    )
}

inline fun <T> Flow<RepositoryResult<T>>.onSuccess(crossinline block: (T) -> Unit): Flow<RepositoryResult<T>> {
    onEach { result -> result.onSuccess(block) }
    return this
}

inline fun <T> Flow<RepositoryResult<T>>.onError(crossinline block: (RepositoryError) -> Unit): Flow<RepositoryResult<T>> {
    onEach { result -> result.onError(block) }
    return this
}
