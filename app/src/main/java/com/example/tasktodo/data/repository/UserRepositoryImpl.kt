package com.example.tasktodo.data.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService): UserRepository{
    override suspend fun getUserProfile(id: String, password: String): List<UserEntity>{
        return apiService.getUserProfile(id, password).map{it.toEntity()}
    }

    override suspend fun getUserTag(tag: String): List<UserEntity> {
        return apiService.getUserTag(tag).map{it.toEntity()}
    }
    override suspend fun createUser(user: UserEntity): UserEntity {
        return apiService.createUser(user).toEntity()
    }

    override suspend fun updateUserInfo(
        id: String,
        user: UserEntity
    ): UserEntity {
        return apiService.updateUserInfo(id, user).toEntity()
    }

    override suspend fun deleteUser(id: String) {
        return apiService.deleteUser(id)
    }
}