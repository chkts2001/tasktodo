package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.LoginError
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.service.LoginValidationService
import com.example.tasktodo.domain.service.registarationService.RegValidationService
import retrofit2.HttpException

class LoginAccountUseCase(private val validator: CustomDataValidator, private val service: LoginValidationService) {
    suspend operator fun invoke(tag: String?, password: String?): Result<UserEntity> {
        val validatorResult = validator.loginParamValidator(tag, password)
        if(validatorResult.isFailure) return Result.failure(validatorResult.exceptionOrNull()!!)
        try {
            val resultValid = service.isAccountAvailable(tag!!, password!!) ?: return Result.failure(LoginError.ErrorNotFound())
            return Result.success(resultValid)
        } catch (e: HttpException) {
            return if (e.code() == 404) Result.failure(LoginError.ErrorNotFound())
            else Result.failure(LoginError.NetworkError(e))
        } catch (e: Exception) {
            return Result.failure(LoginError.NetworkError(e))

        }
    }
}