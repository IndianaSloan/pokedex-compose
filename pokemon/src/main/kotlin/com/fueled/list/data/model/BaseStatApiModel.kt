package com.fueled.list.data.model

import com.fueled.core.data.ApiFields
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseStatApiModel(
    @field:Json(name = ApiFields.BASE_STAT) val value: Int,
    val stat: StatisticApiModel,
)
