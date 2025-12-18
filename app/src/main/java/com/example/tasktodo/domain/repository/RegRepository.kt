package com.example.tasktodo.domain.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.domain.entity.UserEntity

interface RegRepository {
    suspend fun gerUserEmail(email: String): List<String>
    suspend fun getUserTag(tag: String): List<String>
    suspend fun createUser(user: UserEntity): UserEntity
}