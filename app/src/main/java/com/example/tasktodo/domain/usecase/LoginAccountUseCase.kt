package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.LoginError
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.LoginRepository
import retrofit2.HttpException

class LoginAccountUseCase(private val repository: LoginRepository, private val validator: CustomDataValidator) {
    suspend operator fun invoke(tag: String?, password: String?): Result<UserEntity> {
        val validatorResult = validator.loginParamValidator(tag, password)
        if(validatorResult.isFailure){
            return Result.failure(validatorResult.exceptionOrNull()!!)
        }

        try {
            val users = repository.getUserProfile(tag!!, password!!)
            val findUser = users.find { validator.twoStringValidator(it.tag, tag) && validator.twoStringValidator(it.password, password)
            } ?: throw IllegalArgumentException("HTTP 404 ")
            return Result.success(findUser)
        } catch (e: HttpException) {
            return if (e.code() == 404) Result.failure(LoginError.ErrorNotFound())
            else Result.failure(LoginError.NetworkError(e))
        } catch (e: Exception) {
            return if (e.message!!.contains("404")) {
                Result.failure(LoginError.ErrorNotFound())
            } else {
                Result.failure(LoginError.NetworkError(e))
            }
        }
    }
}