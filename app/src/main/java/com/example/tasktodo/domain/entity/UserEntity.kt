package com.example.tasktodo.domain.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    val user_id: String,
    val tag: String,
    val email: String,
    val password: String,
    val avatar: String,
)