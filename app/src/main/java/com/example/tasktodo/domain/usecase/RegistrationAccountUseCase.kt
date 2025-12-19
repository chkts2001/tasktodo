package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.error.RegErrorParam
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.service.CheckParamCorrectService
import com.example.tasktodo.domain.service.registarationService.RegValidationService
import retrofit2.HttpException

class RegistrationAccountUseCase(private val repository: RegRepository, private val userService: RegValidationService) {
    suspend operator fun invoke(formedUser: UserEntity): Result<UserEntity>{
        return userService.validateUser(formedUser).mapCatching { repository.createUser(formedUser) }
    }
}
