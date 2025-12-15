package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.UserEntity

interface GetUserRepository {
    suspend fun getUserInfo(id: String): UserEntity
}

interface SetUserRepository {
    suspend fun createUser(user: UserEntity): UserEntity
    suspend fun updateUserInfo(id: String, user: UserEntity): UserEntity
    suspend fun deleteUser(id: String)
}