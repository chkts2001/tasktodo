package com.example.tasktodo.data.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.GetUserRepository
import com.example.tasktodo.domain.repository.SetUserRepository
import com.example.tasktodo.domain.repository.TaskReadRepository

class GetUserRepositoryImpl(private val apiService: ApiService): GetUserRepository {
    override suspend fun getUserInfo(id: String, password: String): List<UserEntity>{
        return apiService.getUserInfo(id, password).map{it.toEntity()}
    }
}

class SetUserRepositoryImpl(private val apiService: ApiService): SetUserRepository{
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