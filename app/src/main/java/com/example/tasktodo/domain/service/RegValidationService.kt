package com.example.tasktodo.domain.service

import com.example.tasktodo.domain.entity.UserEntity

interface RegValidationService {
    suspend fun isEmailAvailable(email: String): Boolean
    suspend fun isTagAvailable(tag: String): Boolean
}