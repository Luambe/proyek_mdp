package com.example.code.data.utils

import com.squareup.moshi.Json

data class UserUpdateRequest(
    @Json(name = "user_username")
    val username: String,
    @Json(name = "user_email")
    val email: String,
    @Json(name = "user_phone")
    val phone: String
)