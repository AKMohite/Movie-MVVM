package com.mak.telflix.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


interface BaseResponse<T>

@JsonClass(generateAdapter = true)
data class SuccessResponse<T>(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<T>? = null,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
) : BaseResponse<T>

@JsonClass(generateAdapter = true)
data class ErrorResponse<T>(
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "status_message")
    val statusMessage: String?,
    @Json(name = "success")
    val success: Boolean,
) : BaseResponse<T>