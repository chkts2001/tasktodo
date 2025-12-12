package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.UserEntity

interface UserRepository {
    suspend fun getUser(): List<UserEntity>
}