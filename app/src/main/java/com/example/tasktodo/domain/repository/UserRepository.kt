package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.UserEntity


interface UserRepository {
    suspend fun getUserProfile(id: String, password: String): List<UserEntity>
    suspend fun getUserTag(tag: String): List<UserEntity>
    suspend fun createUser(user: UserEntity): UserEntity
    suspend fun updateUserInfo(id: String, user: UserEntity): UserEntity
    suspend fun deleteUser(id: String)
}