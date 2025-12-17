package com.example.tasktodo.domain.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.domain.entity.UserEntity

interface RegRepository {
    suspend fun getUserTag(tag: String): List<UserEntity>
    suspend fun createUser(user: UserEntity): UserEntity
}