package com.example.tasktodo.data.api.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val tag: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("avatar") val avatar: String,
)