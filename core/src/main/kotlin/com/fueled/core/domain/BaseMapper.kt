package com.fueled.core.domain

import com.fueled.core.data.model.ApiResult
import com.fueled.core.data.model.ResponseApiModel
import com.fueled.core.domain.model.RepositoryError
import com.fueled.core.domain.model.RepositoryResult

abstract class BaseMapper {

    /**
     * Unpacks the data from a ResponseApiModel
     *
     * @param callName for logging
     */
    fun <T> mapApiResponse(
        apiResult: ApiResult<ResponseApiModel<T>>,
        callName: String
    ): RepositoryResult<T> {
        return map(apiResult) {
            it.data?.results ?: throw ApiMappingException("Missing payload from $callName")
        }
    }

    private fun <T, R> map(
        apiResult: ApiResult<T>,
        transform: (ApiResult.Success<T>) -> R
    ): RepositoryResult<R> {
        return when (apiResult) {
            is ApiResult.Success -> RepositoryResult.success(transform(apiResult))
            is ApiResult.Failure -> mapError(apiResult)
        }
    }

    private fun <R> mapError(apiResult: ApiResult.Failure): RepositoryResult<R> {
        return RepositoryResult.error(
            RepositoryError.fromException(
                apiResult.throwable,
                apiResult.message
            )
        )
    }
}
