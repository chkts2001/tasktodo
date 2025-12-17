package com.example.tasktodo.domain.usecase

import android.util.Log
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.repository.UserRepository
import retrofit2.HttpException

class RegistrationAccountUseCase(private val repository: RegRepository, private val validator: CustomDataValidator) {
    suspend operator fun invoke(formedUser: UserEntity): Result<UserEntity>{
        val validatorResult = validator.regParamValidator(listOf(formedUser.tag, formedUser.password, formedUser.email))
        if(validatorResult.isFailure){
            return Result.failure(RegError.MissingBothParam())
        }
        try {
            val usersTag = repository.getUserTag(formedUser.tag)
            usersTag.find { validator.twoStringValidator(it.tag, formedUser.tag) } ?: return Result.success(repository.createUser(formedUser))
            return Result.failure(RegError.NameOccupied(formedUser.tag))
        }catch (e: HttpException){
            return when(e.code()){
                404 -> Result.success(repository.createUser(formedUser))
                else -> Result.failure(RegError.NetworkError(e))
            }
        }catch(e: Exception){
            return Result.failure(RegError.NetworkError(e))
        }
    }
}
