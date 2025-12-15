package com.example.tasktodo.data.api.mapper

import com.example.tasktodo.data.api.dto.UserDto
import com.example.tasktodo.domain.entity.UserEntity
import kotlin.String

fun UserDto.toEntity(): UserEntity = UserEntity(
    user_id = user_id,
    tag = tag,
    email = email,
    password = password,
    avatar = avatar
)