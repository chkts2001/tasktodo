package com.example.tasktodo.domain.service.registarationService

import com.example.tasktodo.domain.entity.UserEntity

interface RegValidationService {

    suspend fun validateUser(user: UserEntity): Result<UserEntity>
}