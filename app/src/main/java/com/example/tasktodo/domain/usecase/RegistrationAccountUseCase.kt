package com.example.tasktodo.domain.usecase

import android.util.Log
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.UserRepository
import retrofit2.HttpException

class RegistrationAccountUseCase(private val repository: UserRepository, private val validator: CustomDataValidator) {
    suspend operator fun invoke(formedUser: UserEntity): Result<UserEntity>{
        try {
            //var tempUser: UserEntity? = null
            val usersTag = repository.getUserTag(formedUser.tag)
            usersTag.find { validator.twoStringValidator(it.tag, formedUser.tag) } ?: return Result.success(repository.createUser(formedUser))
            return Result.failure(IllegalArgumentException("Имя @${formedUser.tag} уже занято"))
        }catch (e: HttpException){
            when(e.code()){
                404 -> {
                    Log.d("debug", "create in catch 404")
                    return Result.success(repository.createUser(formedUser))
                }
                else -> {
                    Log.d("debug", "Ошибка: ${e.message}")
                    return Result.failure(e)
                }
            }
        }catch(e: Exception){
            Log.d("debug", "Ошибка: ${e.message}")
            return Result.failure(e)
        }
    }
}
