package com.example.tasktodo.domain.service

import com.example.tasktodo.domain.entity.UserEntity

interface LoginValidationService {
    suspend fun isAccountAvailable(tag: String, password: String): UserEntity?
}