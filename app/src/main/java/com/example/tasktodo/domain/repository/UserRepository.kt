package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.UserEntity


interface UserRepository {
    suspend fun updateUserInfo(id: String, user: UserEntity): UserEntity
    suspend fun deleteUser(id: String)
}