package com.example.tasktodo.data.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.LoginRepository

class LoginRepositoryImpl(private val apiService: ApiService): LoginRepository{
    override suspend fun getUserProfile(
        id: String,
        password: String
    ): List<UserEntity> {
        return apiService.getUserProfile(id, password).map { it.toEntity() }
    }

}