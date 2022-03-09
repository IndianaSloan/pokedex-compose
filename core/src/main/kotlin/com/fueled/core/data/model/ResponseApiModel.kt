package com.fueled.core.data.model

import com.fueled.core.data.ApiFields
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseApiModel<T>(

    val count: Int,

    @field:Json(name = ApiFields.NEXT)
    val nextPageUrl: String?,

    @field:Json(name = ApiFields.PREVIOUS)
    val previousPageUrl: String?,

    val results: T
)
