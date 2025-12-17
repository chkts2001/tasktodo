package com.example.tasktodo.domain.usecase

import android.util.Log
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.LoginRepository
import com.example.tasktodo.domain.repository.UserRepository
import retrofit2.HttpException

class LoginAccountUseCase(private val repository: LoginRepository, private val validator: CustomDataValidator) {
    suspend operator fun invoke(tag: String?, password: String?): Result<UserEntity> {
        try {
            //var findUser: UserEntity? = null
            if(tag != null && password != null) {
                val users = repository.getUserProfile(tag, password)
                val findUser = users.find { validator.twoStringValidator(it.tag, tag) && validator.twoStringValidator(it.password, password)
                } ?: throw IllegalArgumentException("HTTP 404 ")
                return Result.success(findUser)
            }else{
                if(validator.twoStringValidator(tag, null) && validator.twoStringValidator(password, null) ) throw IllegalArgumentException("3")
                else if(validator.twoStringValidator(password, null)) throw IllegalArgumentException("2")
                else throw IllegalArgumentException("1")
            }
        } catch (e: HttpException) {
            return if (e.code() == 404) Result.failure(IllegalArgumentException("Неверный логин или пароль"))
            else Result.failure(e)
        } catch (e: Exception) {
            return if (e.message!!.contains("404")) {
                Result.failure(IllegalArgumentException("Неверный логин или пароль"))
            } else {
                Result.failure(e)
            }
        }
    }
}