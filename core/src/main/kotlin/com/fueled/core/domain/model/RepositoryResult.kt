package com.fueled.core.domain.model

class RepositoryResult<T> private constructor() {

    var result: T? = null
        private set

    var error: RepositoryError? = null
        private set

    companion object {
        fun <T> success(result: T) = RepositoryResult<T>().apply {
            this.result = result
        }

        fun <T> error(error: RepositoryError) = RepositoryResult<T>().apply {
            this.error = error
        }
    }
}

inline fun <T, R> RepositoryResult<T>.flatMap(mapper: (T) -> R): RepositoryResult<R> {
    return result?.let {
        try {
            RepositoryResult.success(mapper(it))
        } catch (e: Throwable) {
            RepositoryResult.error(
                RepositoryError.fromException(e, null)
            )
        }
    } ?: RepositoryResult.error(error!!) // Safe double bang as either result or error must contain a value
}

inline fun <T> RepositoryResult<T>.onSuccess(block : (result: T) -> Unit): RepositoryResult<T> {
    if (result != null) {
        block(result!!)
    }
    return this
}

inline fun <T> RepositoryResult<T>.onError(block: (error: RepositoryError) -> Unit): RepositoryResult<T> {
    if (error != null) {
        block(error!!)
    }
    return this
}
