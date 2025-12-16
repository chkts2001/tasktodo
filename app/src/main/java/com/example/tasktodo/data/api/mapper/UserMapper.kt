package com.example.tasktodo.data.api.mapper

import com.example.tasktodo.data.api.dto.UserDto
import com.example.tasktodo.domain.entity.UserEntity
import kotlin.String

fun UserDto.toEntity(): UserEntity = UserEntity(
    tag = tag,
    email = email,
    password = password,
    avatar = avatar
)