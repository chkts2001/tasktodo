package com.example.tasktodo.data.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.RegRepository

class RegRepositoryImpl(private val apiService: ApiService): RegRepository {
    override suspend fun getUserTag(tag: String): List<UserEntity> {
        return apiService.getUserTag(tag).map{it.toEntity()}
    }
    override suspend fun createUser(user: UserEntity): UserEntity {
        return apiService.createUser(user).toEntity()
    }
}