package com.fueled.core.data.model

/**
 * A sealed wrapper class to wrap the results of all API calls
 */
sealed class ApiResult<out T> {
    class Success<out T>(val data: T?) : ApiResult<T>()
    class Failure(val throwable: Throwable, val message: String? = null) : ApiResult<Nothing>()
}
