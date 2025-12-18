package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.error.RegErrorParam
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.service.RegValidationService
import retrofit2.HttpException

class RegistrationAccountUseCase(private val repository: RegRepository, private val validator: CustomDataValidator, private val userService: RegValidationService) {
    suspend operator fun invoke(formedUser: UserEntity): Result<UserEntity>{
        val validatorResult = validator.regParamValidator(listOf(formedUser.tag, formedUser.password, formedUser.email))
        if(validatorResult.isFailure){
            return Result.failure(RegError.MissingBothParam())
        }
        try {
            try {
                if (userService.isEmailAvailable(formedUser.email)) return Result.failure(RegError.EmailOccupied())
            }catch (e: HttpException) {
                return when (e.code()) {
                    404 -> {
                        return if (userService.isTagAvailable(formedUser.tag)) Result.failure(RegErrorParam.NameOccupied(formedUser.tag))
                        else Result.success(repository.createUser(formedUser))
                    }
                    else -> Result.failure(RegError.NetworkError(e))
                }
            }
            try {
                if (userService.isTagAvailable(formedUser.tag)) return Result.failure(RegErrorParam.NameOccupied(formedUser.tag))
            }catch (e: HttpException) {
                return when (e.code()) {
                    404 -> Result.success(repository.createUser(formedUser))
                    else -> Result.failure(RegError.NetworkError(e))
                }
            }
            return Result.success(repository.createUser(formedUser))
        }catch(e: Exception){
            return Result.failure(RegError.NetworkError(e))
        }
    }
}
