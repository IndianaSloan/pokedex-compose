package com.fueled.core.data

import com.fueled.core.data.model.ApiResponseException
import com.fueled.core.data.model.ApiResult
import com.fueled.core.data.model.AuthException
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject

abstract class BaseApi {

    @Inject
    lateinit var resourceProvider: com.fueled.core.base.ResourceProvider

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = call.invoke()
            return apiResult(response)
        } catch (e: Exception) {
            when (e) {
                is java.io.IOException -> ApiResult.Failure(e) // Network error
                is HttpException -> { // Unexpected non-2xx error
                    e.response()?.let {
                        ApiResult.Failure(e, it.errorBody()?.string())
                    } ?: ApiResult.Failure(e)
                }
                else -> ApiResult.Failure(e) // Unknown Error
            }
        }
    }

    private fun <T : Any> apiResult(response: Response<T>): ApiResult<T> {
        return when {
            response.isSuccessful -> ApiResult.Success(response.body())
            else -> {
                val errorMessage = parseErrorMessage(response)
                when (response.code()) {
                    HTTP_UNAUTHORIZED -> ApiResult.Failure(AuthException(), errorMessage)
                }
                val errorResponse = response.errorBody()?.string()
                ApiResult.Failure(ApiResponseException(), errorResponse)
            }
        }
    }

    private fun <T> parseErrorMessage(response: Response<T>): String {
        return ""
    }
}
