package com.fueled.core.domain.model

import retrofit2.HttpException
import java.io.IOException

/**
 * [RepositoryError]s are made for the presentation layer by Repositories and they hide away the
 * obscure complexities of network errors, server errors, unexpected API exceptions.
 */
sealed class RepositoryError(cause: Throwable, message: String? = null) :
    Throwable(message, cause) {

    /**
     * The operation has failed due to internet connectivity issues
     */
    class Network(cause: Throwable) : RepositoryError(cause, null)

    /**
     * The server got the request, but responded with an error
     *
     * @param message the error message received from the server.
     */
    class Server(cause: Throwable, message: String? = null) : RepositoryError(cause, message)

    /**
     * Unexpected / Unrecognised failures.
     */
    class Generic(cause: Throwable, message: String? = null) : RepositoryError(cause, message)

    companion object {
        fun fromException(e: Throwable, message: String? = null): RepositoryError {
            return when (e) {
                is HttpException -> Server(e, message)
                is IOException -> Network(e)
                else -> Generic(e, message)
            }
        }
    }
}
