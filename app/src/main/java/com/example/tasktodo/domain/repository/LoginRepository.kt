package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.UserEntity

interface LoginRepository {
    suspend fun getUserProfile(id: String, password: String): List<UserEntity>
}